package com.erick.expensetracker.expensetracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 🎯 SIMPLE Integration Test Example
 * 
 * 📖 WHAT IS THIS FOR?
 * This demonstrates automated testing vs your manual .http file testing
 * 
 * 🤔 WHY USE INTEGRATION TESTS?
 * 
 * Manual Testing (.http files):
 * ✋ You manually click "Send Request" 
 *  You manually check each response
 * ⏰ Takes time to test everything
 * 😴 Easy to forget testing scenarios
 * 
 * Automated Testing (this file):
 * ⚡ Tests run automatically with one click
 * 🚀 All scenarios tested in seconds  
 * 🛡️ Catches bugs immediately when you make changes
 * 📊 Professional development standard
 * 
 * 💼 REAL WORLD USAGE:
 * - CI/CD pipelines run these automatically
 * - Prevents broken code from reaching production
 * - Much faster than manual testing
 * - Required in professional development
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleExpenseIntegrationTest {

    // 🎯 CI/CD MAGIC HAPPENS HERE:
    // This annotation starts your ENTIRE Spring Boot app for testing!
    // In CI/CD pipelines, this ensures your app works exactly like production

    @LocalServerPort
    private int port; // 🚀 Spring gives us a random port (like 57862)

    @Autowired
    private TestRestTemplate restTemplate; // 🧪 Our HTTP client to test APIs

    /**
     * 🧪 Test 1: CI/CD Guardian - Protects your /expenses endpoint
     * 
     * 🛡️ IN CI/CD PIPELINE: If someone's code breaks this endpoint,
     * this test will FAIL and BLOCK deployment to production!
     */
    @Test
    void shouldGetAllExpenses() {
        // 🚀 SIMULATION: This is like a robot clicking "Send Request" in your .http file
        ResponseEntity<String> response = restTemplate.getForEntity(
            "http://localhost:" + port + "/expenses", 
            String.class
        );

        // 🛡️ PROTECTION: If this fails, CI/CD stops deployment immediately!
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
        System.out.println("✅ GET /expenses works! Status: " + response.getStatusCode());
        // 📊 In CI/CD dashboards, you'll see: "shouldGetAllExpenses ✅ PASSED"
    }

    /**
     * 🧪 Test 2: CI/CD Guardian - Protects your error handling 
     * 
     * 🎯 REAL SCENARIO: In production, users will try invalid URLs
     * This test ensures your GlobalExceptionHandler always works correctly
     */
    @Test
    void shouldReturn404ForNonExistentExpense() {
        // 🚨 SIMULATION: User tries to access expense that doesn't exist
        ResponseEntity<String> response = restTemplate.getForEntity(
            "http://localhost:" + port + "/expenses/99999", 
            String.class
        );

        // 🛡️ PROTECTION: Ensures your error handling NEVER breaks in production
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        
        System.out.println("✅ Error handling works! Status: " + response.getStatusCode());
        // 📊 CI/CD Dashboard: "Error Handling Protection ✅ ACTIVE"
    }

    /**
     * 🧪 Test 3: Verify count endpoint  
     * This replaces manually testing "GET http://localhost:8080/expenses/count/"
     */
    @Test
    void shouldGetExpenseCount() {
        // When - Call count endpoint (note the trailing slash!)
        ResponseEntity<String> response = restTemplate.getForEntity(
            "http://localhost:" + port + "/expenses/count/", 
            String.class
        );

        // Then - Should return count
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
        System.out.println("✅ Count endpoint works! Count: " + response.getBody());
    }

    /**
     * 🎯 SUMMARY: 
     * 
     * Instead of manually testing 3 endpoints (click, check, repeat)...
     * This file tests ALL 3 automatically in seconds!
     * 
     * 🚀 TO RUN: Right-click this file → "Run Tests"
     * 
     * 💡 NEXT LEVEL: You can add more complex tests that:
     * - Create expenses via POST
     * - Update via PUT  
     * - Delete via DELETE
     * - Test all your DTO transformations
     * - Validate your ResponseEntity status codes
     */
}