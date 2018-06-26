package rest.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestControllerExample {

   // Handler method to produce text response
   @GetMapping(path = "/get/text", produces = MediaType.TEXT_PLAIN_VALUE)
   public ResponseEntity<String> getText() {
      return ResponseEntity
            .ok()
            .body("Spring MVC - REST Controller Hello World example.");
   }

   // Handler method to produce JSON response
   @GetMapping(path = "/get/json", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<String>> getJSON() {
      List<String> list = new ArrayList<>();
      list.add("One");
      list.add("Two");
      list.add("Three");
      return ResponseEntity
            .ok()
            .cacheControl(CacheControl.noCache())
            .body(list);
   }

   // Handler method to produce XML response
   @GetMapping(path = "/get/xml", produces = MediaType.APPLICATION_XML_VALUE)
   public ResponseEntity<String> getXML() {
      String xml = "<user><id>12</id><name>John</name></user>";
      return ResponseEntity
            .ok()
            .header("myheader", "myvalue") // add custom headers
            .body(xml);
   }

   // Handler method to consume JSON request and produce text response
   @PostMapping(path = "/post/json", consumes = MediaType.APPLICATION_JSON_VALUE, 
         produces = MediaType.TEXT_PLAIN_VALUE)
   public ResponseEntity<String> postJSON(@RequestBody List<String> body) {
      System.out.println(body);
      // Process request
      //....
      return ResponseEntity
            .ok()
            .body("Done");
   }

   // Handler method to consume XML request and produce text response
   @PostMapping(path = "/post/xml", consumes = MediaType.APPLICATION_XML_VALUE, 
         produces = MediaType.TEXT_PLAIN_VALUE)
   public ResponseEntity<String> postJSON(@RequestBody String body) {
      System.out.println(body);
      // Process request
      //....
      return ResponseEntity
            .ok()
            .body("Done");
   }
}