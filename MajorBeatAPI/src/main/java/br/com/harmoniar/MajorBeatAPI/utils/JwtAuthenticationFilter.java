package br.com.harmoniar.MajorBeatAPI.utils;

import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        boolean ignore = path.equals("/Musico/login")
                || path.equals("/Musico/cadastrar")
                || path.equals("/Contratante/login")
                || path.equals("/Contratante/cadastrar");
        System.out.println("shouldNotFilter " + path + " = " + ignore);
        return ignore;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // 🔹 Se não tiver token, apenas segue o fluxo (sem lançar erro)
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            Long userId = JwtUtil.extrairUsuarioId(token);
            String roleName = JwtUtil.extrairRole(token);

            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(roleName));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            authorities
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);


            System.out.println("Token extraído: " + token);
            System.out.println("UserId extraído: " + userId);
            System.out.println("Role extraída: " + roleName);


        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
