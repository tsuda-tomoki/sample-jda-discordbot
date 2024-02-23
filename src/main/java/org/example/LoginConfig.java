package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig implements ApplicationRunner {
  @Value("${token}")
  private String BOT_TOKEN;

  @Override
  public void run(ApplicationArguments args) {
      JDA jda = JDABuilder.createDefault(BOT_TOKEN)
          .setRawEventsEnabled(true)
          .enableIntents(GatewayIntent.MESSAGE_CONTENT)
          .addEventListeners(new RoomieCheckEvent())
          .build();

      jda.updateCommands().queue();
  }
}
