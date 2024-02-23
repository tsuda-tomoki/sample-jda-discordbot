package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class RoomieCheckEvent extends ListenerAdapter {

  @Override
  public void onMessageReceived(MessageReceivedEvent messageReceivedEvent) {

    if (messageReceivedEvent.getAuthor().isBot()) return;
    if (messageReceivedEvent.getAuthor().getName().equals("koharina")) return;
    if (messageReceivedEvent.getAuthor().getName().equals("vivid_naria")) return;


    if (messageReceivedEvent.getMessage().getContentRaw().equals("?roomie")) {
      for (String message : getRoomieURLs()) {
        messageReceivedEvent.getChannel().sendMessage(message).queue();
      }
    }

    if (messageReceivedEvent.getMessage().getContentRaw().equals("?ex:stop"))
      System.exit(0);
  }

  private List<String> getRoomieURLs() {
    try {

      List<String> elementList = new ArrayList<>();

      Document doc = Jsoup.connect("https://www.roomie.jp/articles/").get();

      Elements elementsURL = doc.select(":is(section, article) > :is(a)");

      for (Element element : elementsURL) {
        if (!Objects.equals(element.absUrl("href"), "https://twitter.com/roomiejp"))
          elementList.add(element.absUrl("href"));

        if (elementList.size() == 5) break;
      }
      return elementList;

    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }
}
