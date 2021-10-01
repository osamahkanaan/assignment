package com.smart.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.assignment.entity.Author;
import com.smart.assignment.entity.Book;
import com.smart.assignment.entity.Classification;
import com.smart.assignment.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentApplicationTests {
    
        @Autowired
        private MockMvc mockMvc;
    
        @Autowired
        private BookService bookService;
        
        @Test
        void BookControllerListTest() throws Exception{
            
            RequestBuilder request =
                    MockMvcRequestBuilders.get("/books/list")
                    .accept(MediaType.APPLICATION_JSON);;
            mockMvc.perform(request)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
        }
        
        @Test
        void BookControllerReadTest() throws Exception{
            
            RequestBuilder request =
                    MockMvcRequestBuilders.get("/books/1")
                    .accept(MediaType.APPLICATION_JSON);;
            mockMvc.perform(request)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        }
        
        @Test
        public void BookControllerCreateTest() throws Exception 
        {
            Classification classification = new Classification(1L);
            Author author = new Author(1L);
            RequestBuilder request =
                    MockMvcRequestBuilders.post("/books/create")
                        .content(asJsonString(new Book(null, "Book2", "test", 15.5, "123456789", author, classification)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);
            mockMvc.perform(request)
              .andExpect(status().isCreated())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        }
        
        @Test
        public void BookControllerUpdateTest() throws Exception 
        {
            Book b = bookService.getLastBook();
            b.setName("Book upate test");
            RequestBuilder request =
                    MockMvcRequestBuilders.put("/books/update/" + b.getId())
                        .content(asJsonString(b))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);
            mockMvc.perform(request)
              .andExpect(status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Book upate test"));
        }
        
        @Test
        public void BookControllerDeleteTest() throws Exception 
        {
            Book b = bookService.getLastBook();
            RequestBuilder request =
                    MockMvcRequestBuilders.delete("/books/delete/" + b.getId());
            mockMvc.perform(request)
              .andExpect(status().isAccepted());
        }
        
        public static String asJsonString(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}
