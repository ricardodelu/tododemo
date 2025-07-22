package com.ricardodelu.tododemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardodelu.tododemo.model.Post;
import com.ricardodelu.tododemo.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Test
    void testCreatePost() throws Exception {
        Post newPost = new Post("Test Title", "Test Content", "Test Author");

        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPost)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void testGetAllPosts() throws Exception {
        // Create test posts
        Post post1 = new Post("First Post", "First Content", "Author1");
        Post post2 = new Post("Second Post", "Second Content", "Author2");
        
        postRepository.save(post1);
        postRepository.save(post2);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("First Post"))
                .andExpect(jsonPath("$[0].content").value("First Content"))
                .andExpect(jsonPath("$[0].author").value("Author1"))
                .andExpect(jsonPath("$[1].title").value("Second Post"))
                .andExpect(jsonPath("$[1].content").value("Second Content"))
                .andExpect(jsonPath("$[1].author").value("Author2"));
    }

    @Test
    void testGetPostById() throws Exception {
        // Create and save a test post
        Post testPost = new Post("Test Post", "Test Content", "Test Author");
        Post savedPost = postRepository.save(testPost);

        mockMvc.perform(get("/posts/" + savedPost.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedPost.getId()))
                .andExpect(jsonPath("$.title").value("Test Post"))
                .andExpect(jsonPath("$.content").value("Test Content"))
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    void testGetPostByIdNotFound() throws Exception {
        mockMvc.perform(get("/posts/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletePost() throws Exception {
        // Create and save a test post
        Post testPost = new Post("Post to Delete", "Delete Content", "Delete Author");
        Post savedPost = postRepository.save(testPost);

        mockMvc.perform(delete("/posts/" + savedPost.getId()))
                .andExpect(status().isNoContent());
        
        // Verify the post was deleted
        mockMvc.perform(get("/posts/" + savedPost.getId()))
                .andExpect(status().isNotFound());
    }
}