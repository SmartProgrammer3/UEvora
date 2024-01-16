package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    //Dar autorização
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        final String sqlUserName = "select u.username, u.userpassword, u.enable from tabelaDeUtilizadores u where u.username = ?";
        final String sqlAuthorities = "select ur.username, ur.user_role from roleUtilizadores ur where ur.username = ?";
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(sqlUserName)
                .authoritiesByUsernameQuery(sqlAuthorities).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /** Acesso do STAFF **/
                .antMatchers("/registarUmEvento", "/registarTempoParticipante", "/eventoconfirmado").access("hasRole('STAFF')")

                /** Acesso do ATLETA**/
                .antMatchers("/inscricao", "/inscricoesRealizadas", "/pagamento").access("hasRole('ATLETA')")

                /** Acesso público **/
                .antMatchers("/", "/sobreNos", "/newuser", "/eventosPesquisar", "/register",
                        "/eventosPassados", "/eventosADecorrer", "/informacaoEvento", "/classificacoes", "/tempoParticipante",
                        "/eventosFuturos", "/login", "/static/**", "/error**", "/registoconfirmado")
                .permitAll().anyRequest().authenticated()// ,																							// /,login
                .and()// ,
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
