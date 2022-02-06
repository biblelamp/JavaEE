@RestController
class WebApp {
    @GetMapping("/")
    String welcome() {
        "<h1><font face='verdana'>Hello Spring Boot!</font></h1>"
    }
}