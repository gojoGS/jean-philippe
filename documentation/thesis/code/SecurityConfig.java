@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // ...
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            // ...
            .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/app/public/**").permitAll()
            .antMatchers("/app/restaurant/**").hasRole("RESTAURANT")
            .antMatchers("/app/end-user/**").hasRole("END_USER")
            // ...
    }
    // ,,,
}
