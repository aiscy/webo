package online.senpai

import org.junit.Test
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.assertTrue

private class KGenericContainer(dockerImageName: String) : GenericContainer<KGenericContainer>(dockerImageName) //  Kill me

@Testcontainers
internal class Playground {

    @Container
    private val nginxContainer = KGenericContainer("nginx:1.16.0")

    @Test
    fun test() {
        nginxContainer
//            .withClasspathResourceMapping("events {\n  worker_connections   2000;\n  use epoll;\n}\n\nhttp {\n  upstream frontend {\n    server 172.17.0.1:8081;\n  }\n\n  upstream backend {\n    server 172.17.0.1:8082;\n  }\n\n  server {\n    listen 8080;\n    server_name q6kfjhnpdimvjhkrk2fm.senpai.online;\n    root /var/www/static;\n    server_tokens off;\n    include /etc/nginx/mime.types;\n    proxy_set_header X-Real-IP $remote_addr;\n    proxy_set_header X-Forwarded-Host $host:$server_port;\n    proxy_set_header X-Forwarded-Server $host;\n    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;\n    add_header X-Frame-Options \"SAMEORIGIN\";\n    add_header X-Content-Type-Options \"nosniff\";\n    add_header X-XSS-Protection \"1; mode=block\";\n    add_header Referrer-Policy \"same-origin\";\n    add_header Content-Security-Policy \"default-src 'none'; script-src 'self'; connect-src 'self'; img-src 'self'; style-src 'self'; font-src 'self';\";\n\n    location /user {\n      proxy_pass http://backend;\n    }\n\n\n    location / {\n      #proxy_set_header Upgrade $http_upgrade;\n      #proxy_set_header Connection \"upgrade\";\n      try_files $uri $uri/ /index.html;\n    }\n  }\n}\n", "/etc/nginx/nginx-dev.conf", BindMode.READ_ONLY)
//            .setPortBindings()
        assertTrue(nginxContainer.isRunning)
    }
}
