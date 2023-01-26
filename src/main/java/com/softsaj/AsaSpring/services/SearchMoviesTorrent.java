package com.softsaj.AsaSpring.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.softsaj.AsaSpring.models.Torrent;
import com.softsaj.AsaSpring.util.convertHtmlIntoData;

public class SearchMoviesTorrent {
	
	
	public List<Torrent>  SearchTorrents(String seasons, String tipo, String query,String query_en, int category ) {
		List<Torrent> portugues = new ArrayList<>();
		List<Torrent> ingles  = new ArrayList<>();
		if(tipo.equals("0")) {
			for(int i =1 ; i<=Integer.parseInt(seasons)+5; i++) {
			String url = "https://www1.thepiratebay3.to/s/page/"+i+"/?q="+query.replace(" ", "+")+"&category=0";//+"&category="+category;
			//System.out.println(url);
			portugues.addAll(new convertHtmlIntoData(executeGet(url, " ")).convert(0,tipo));
			
			String url_en =  "https://www1.thepiratebay3.to/s/page/"+i+"/?q="+query_en.replace(" ", "+")+"&category=0";//+"&category="+category;
			 ingles.addAll(new convertHtmlIntoData(executeGet(url_en, " ")).convert(1,tipo));
			}
		}else {
		String url = "https://www1.thepiratebay3.to/s/?q="+query.replace(" ", "+");//+"&category="+category;
		portugues = new convertHtmlIntoData(executeGet(url, " ")).convert(0,tipo);
		
		String url_en = "https://www1.thepiratebay3.to/s/?q="+query_en.replace(" ", "+");//+"&category="+category;
		 ingles = new convertHtmlIntoData(executeGet(url_en, " ")).convert(1,tipo);
		}
		
		//String url_rargb = "https://rargb.to/search/?search="+query.replace(" ", "+");
		//List<Torrent> portugues_rargb = new convertHtmlIntoData(executeGet(url_rargb, " ")).convert(2);
		
		//String url_rargb_en = "https://rargb.to/search/?search="+query_en.replace(" ", "+");
		//List<Torrent> portugues_rargb_en = new convertHtmlIntoData(executeGet(url_rargb_en, " ")).convert(2);
		
		List<Torrent> torrents = new ArrayList<>();
		//Busca Torrent ThePirateBay 
		torrents.addAll(portugues);
		torrents.addAll(ingles);
		
		if(tipo.equals("0")) {
		//Ordena Por Temporada
		Collections.sort(torrents, new Comparator<Torrent>() {
		      @Override
		      public int compare(final Torrent object1, final Torrent object2) {
		          return (object1.getTemporada() == null ? "0" : object1.getTemporada()).compareTo(object2.getTemporada() == null ? "0" : object2.getTemporada());
		      }
		  });
		;
		
		//ORdena Por Epidosio
		
		Collections.sort(torrents, new Comparator<Torrent>() {
		      @Override
		      public int compare(final Torrent object1, final Torrent object2) {
		          return (object1.getEpisodio() == null ? "0" : object1.getEpisodio()).compareTo(object2.getEpisodio()== null ? "0" : object2.getEpisodio());
		      }
		  });
		
		
		}
		/*Set<Torrent> uniqueGas = new HashSet<Torrent>(torrents);
		//System.out.println("Unique gas count: " + uniqueGas..size());
		//System.out.println();
		List<Torrent> torrents_1 =  new ArrayList<Torrent>();
		torrents_1.addAll(uniqueGas);*/
		
		return torrents;
		
	}
	
	public String translate(String query) {
		
		return query; //executeGet("https://libretranslate.com/?source=pt&target=en&q=texto"," ");
		/*Translator translate = Translator.getInstance();
		String query_traduzida = translate.translate("Hello!", Language.ENGLISH, Language.ROMANIAN);
		System.out.println(query_traduzida); // "Bună ziua!" 
		return query_traduzida;*/
	}
	
	public static String executeGet(String targetURL, String urlParameters) {
		  HttpURLConnection connection = null;

		  try {
		    //Create connection
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		    connection.setRequestProperty("Content-Length", 
		        Integer.toString(urlParameters.getBytes().length));
		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.writeBytes(urlParameters);
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		     // response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		}
}
