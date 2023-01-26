package com.softsaj.AsaSpring.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsaj.AsaSpring.models.Torrent;

public class convertHtmlIntoData {
	
	Logger logger = LoggerFactory.getLogger(convertHtmlIntoData.class);
	
	private String response;
	
	public convertHtmlIntoData(String response) {
		this.response = response;
	}
	
	public List<Torrent>  convert(int language, String tipo) {
		List<Torrent> torrents = new ArrayList<>();
		
		if(tipo.equals("0")) {
			response.indexOf("<body>");
			String new_response = response.substring(response.indexOf("<body>"));
			new_response = new_response.replace("&","-AntesI-")
					.replace("<br/>", "")
					.replace("<br>", "")
					.replace("<img", "");
			if(new_response.contains("</block>")) {

				new_response = new_response.substring(0,new_response.indexOf("</block>")).replace("<body>","");
			}else {
				new_response = new_response.substring(0,new_response.indexOf("</body>")).replace("</block>","");
			}
			JSONObject xmlJSONObj = XML.toJSONObject(new_response);

			String jsonPrettyPrintString = xmlJSONObj.toString(4);
			
			JSONArray lista_completa = new JSONArray();
			try {
			 lista_completa = xmlJSONObj.getJSONArray("div")
					.getJSONObject(1)
					.getJSONObject("div")
					.getJSONObject("table")
					.getJSONObject("tbody")
					.getJSONArray("tr");
			
			}catch (Exception e) {
				
			} 
			System.out.println("Tamanho Total: "+lista_completa.length());
			
			if(language==0) {//Br
				for(int i = 0; i < lista_completa.length(); i++) {

					try {
						JSONArray objeto = lista_completa.getJSONObject(i).getJSONArray("td");
						//System.err.println("Nome: "+ nome);
						String nome = objeto.getJSONObject(1).getJSONObject("a").getString("content");
						if(nome.toLowerCase().contains("dublado".toLowerCase()) || nome.toLowerCase().contains("Dual Audio".toLowerCase()) || nome.toLowerCase().contains("pt-BR".toLowerCase())) {

							Torrent tor = new Torrent();	
							tor.setName(nome);
							tor.setUrl(objeto.getJSONObject(3).getJSONObject("nobr").getJSONObject("a").getString("href").toString().replace("-AntesI-","&"));//.getString("content"));
							tor.setIdioma("pt-BR");
							
							Pattern p = Pattern.compile("S0([^,]*)E");
							Matcher m = p.matcher(tor.getName());
							if(m.find()) {
								String Nome = tor.getName().substring(tor.getName().indexOf(m.group(1)));
								String Temporada = Nome.substring(0,1);
								System.err.println("Nome: "+ Nome+ "/n" + "Temporada: "+Temporada);
								tor.setTemporada(Temporada);
								tor.setEpisodio(Nome.substring(2,4));
								tor.setTipo("0");
							}
							tor.setTamanho(objeto.getJSONObject(4).get("content").toString());
							tor.setSeeders(objeto.getJSONObject(5).get("content").toString());
							tor.setType(objeto.getJSONObject(0).getJSONObject("a").get("content").toString().replace("-AntesI-","&").replace("&gt;", ">"));
							torrents.add(tor);
							
						}
					}catch(Exception e) {
						logger.error(e.getLocalizedMessage());
					}


				}
			}
			if(language==1) {
			for(int i = 0; i < lista_completa.length() ; i++) {
			
				try {
					Torrent tor = new Torrent();
					JSONArray objeto = lista_completa.getJSONObject(i).getJSONArray("td");
					//System.err.println(objeto);
					tor.setName(objeto.getJSONObject(1).getJSONObject("a").getString("content"));
					tor.setUrl(objeto.getJSONObject(3).getJSONObject("nobr").getJSONObject("a").getString("href").toString().replace("-AntesI-","&"));//.getString("content"));
						tor.setIdioma("en-US");
						
						Pattern p = Pattern.compile("S0([^,]*)E");
						Matcher m = p.matcher(tor.getName());
						if(m.find()) {
							String Nome = tor.getName().substring(tor.getName().indexOf(m.group(1)));
							String Temporada = Nome.substring(0,1);
							//System.err.println("Nome: "+ Nome.substring(1,2)+ "\t" + "Temporada: "+Temporada);
							tor.setTemporada(Temporada);
							if(Nome.substring(1,2).equals(" ") || Nome.substring(1,2) ==null) {
								tor.setEpisodio("0");
							}else {
							tor.setEpisodio(Nome.substring(2,4));
							}
							tor.setTipo("0");
						}
						
						
						
					tor.setTamanho(objeto.getJSONObject(4).get("content").toString());
					tor.setSeeders(objeto.getJSONObject(5).get("content").toString());
					tor.setType(objeto.getJSONObject(0).getJSONObject("a").get("content").toString().replace("-AntesI-","&").replace("&gt;", ">"));
					
					torrents.add(tor);
				}catch(Exception e) {
					logger.error(e.getLocalizedMessage());
				}
					
				
				}
			}
			return torrents;
		}else {
		try {
			
			response.indexOf("<body>");
			String new_response = response.substring(response.indexOf("<body>"));
			new_response = new_response.replace("&","-AntesI-")
					.replace("<br/>", "")
					.replace("<br>", "")
					.replace("<img", "");
			if(new_response.contains("</block>")) {

				new_response = new_response.substring(0,new_response.indexOf("</block>")).replace("<body>","");
			}else {
				new_response = new_response.substring(0,new_response.indexOf("</body>")).replace("</block>","");
			}
			JSONObject xmlJSONObj = XML.toJSONObject(new_response);

			String jsonPrettyPrintString = xmlJSONObj.toString(4);
			
			JSONArray lista_completa = new JSONArray();
			try {
			 lista_completa = xmlJSONObj.getJSONArray("div")
					.getJSONObject(1)
					.getJSONObject("div")
					.getJSONObject("table")
					.getJSONObject("tbody")
					.getJSONArray("tr");
			
			}catch (Exception e) {
				
			} 
			System.out.println("Tamanho Total: "+lista_completa.length());
			
			if(language==0) {//Br
				for(int i = 0,j=0; i < lista_completa.length() && j<3; i++) {

					try {
						JSONArray objeto = lista_completa.getJSONObject(i).getJSONArray("td");
						String nome = objeto.getJSONObject(1).getJSONObject("a").getString("content");
						if(nome.toLowerCase().contains("dublado".toLowerCase()) || nome.toLowerCase().contains("Dual Audio".toLowerCase()) || nome.toLowerCase().contains("pt-BR".toLowerCase())) {

							Torrent tor = new Torrent();	
							tor.setName(nome);
							tor.setUrl(objeto.getJSONObject(3).getJSONObject("nobr").getJSONObject("a").getString("href").toString().replace("-AntesI-","&"));//.getString("content"));
							tor.setIdioma("pt-BR");
							tor.setTamanho(objeto.getJSONObject(4).get("content").toString());
							tor.setSeeders(objeto.getJSONObject(5).get("content").toString());
							tor.setType(objeto.getJSONObject(0).getJSONObject("a").get("content").toString().replace("-AntesI-","&").replace("&gt;", ">"));
							torrents.add(tor);
							j++;
						}
					}catch(Exception e) {
						logger.error(e.getLocalizedMessage());
					}


				}
			}
			if(language==1) {
			for(int i = 0, j=0; i < lista_completa.length() && j<3; i++) {
			
				try {
					Torrent tor = new Torrent();
					JSONArray objeto = lista_completa.getJSONObject(i).getJSONArray("td");
					tor.setName(objeto.getJSONObject(1).getJSONObject("a").getString("content"));
					tor.setUrl(objeto.getJSONObject(3).getJSONObject("nobr").getJSONObject("a").getString("href").toString().replace("-AntesI-","&"));//.getString("content"));
						tor.setIdioma("en-US");
						
					tor.setTamanho(objeto.getJSONObject(4).get("content").toString());
					tor.setSeeders(objeto.getJSONObject(5).get("content").toString());
					tor.setType(objeto.getJSONObject(0).getJSONObject("a").get("content").toString().replace("-AntesI-","&").replace("&gt;", ">"));
					j++;
					torrents.add(tor);
				}catch(Exception e) {
					logger.error(e.getLocalizedMessage());
				}
					
				
				}
			}
			return torrents;
			
		}catch(Exception e) {
			System.err.print(e.getMessage());
			
			return torrents;
		}
		
		}
            
	}
	
	/*
	 String url = "https://www1.thepiratebay3.to/s/?q=homem+aranha&category=201";
	String sheach = "<!DOCTYPE html>\r\n"
			+ "<html lang=\"en\">\r\n"
			+ "<head>\r\n"
			+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
			+ "<title>The Pirate Bay - The galaxy's most resilient bittorrent site</title>\r\n"
			+ "<meta name=\"description\" content=\"Search for and download any torrent from the pirate bay using search query homem aranha. Direct download via magnet link.\" />\r\n"
			+ "<meta name=\"keywords\" content=\"homem aranha direct download torrent magnet tpb piratebay search\" />\r\n"
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n"
			+ "\r\n"
			+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/css/pirate6.css\" />\r\n"
			+ "<style type=\"text/css\">\r\n"
			+ "        .searchBox{\r\n"
			+ "            margin: 6px;\r\n"
			+ "            width: 300px;\r\n"
			+ "            vertical-align: middle;\r\n"
			+ "            padding: 2px;\r\n"
			+ "            background-image:url('/static/img/icon-https.gif');\r\n"
			+ "            background-repeat:no-repeat;\r\n"
			+ "            background-position: right;\r\n"
			+ "        }\r\n"
			+ "        .detLink {\r\n"
			+ "            font-size: 1.2em;\r\n"
			+ "            font-weight: 400;\r\n"
			+ "        }\r\n"
			+ "        .detDesc {\r\n"
			+ "            color: #4e5456;\r\n"
			+ "        }\r\n"
			+ "        .detDesc a:hover {\r\n"
			+ "            color: #000099;\r\n"
			+ "            text-decoration: underline;\r\n"
			+ "        }\r\n"
			+ "        .sortby {\r\n"
			+ "            text-align: left;\r\n"
			+ "            float: left;\r\n"
			+ "        }\r\n"
			+ "        .detName {\r\n"
			+ "            padding-top: 3px;\r\n"
			+ "            padding-bottom: 2px;\r\n"
			+ "        }\r\n"
			+ "        .viewswitch {\r\n"
			+ "            font-style: normal;\r\n"
			+ "            float: right;\r\n"
			+ "            text-align: right;\r\n"
			+ "            font-weight: normal;\r\n"
			+ "        }\r\n"
			+ "    @media only screen and (max-width: 700px) { #categoriesTable{width:100%} \r\n"
			+ "#main-content{margin-left: 0;margin-right: 0;overflow: auto;} #content{min-width: 100%; max-width: 100%;}#details dl{float:none}.nfo{width:94%} #detailsouterframe{width:100%}  }\r\n"
			+ "</style>\r\n"
			+ "<script src=\"/static/js/jquery.min.js\" type=\"text/javascript\"></script>\r\n"
			+ "<script src=\"/static/js/tpb.js\" type=\"text/javascript\"></script>\r\n"
			+ "<script language=\"javascript\" type=\"text/javascript\">if (top.location != self.location) {top.location.replace(self.location);}</script>\r\n"
			+ "<style>body{ padding: 10px 0; } #main-content{ margin-left: 0; margin-right: 0;margin-bottom: 20px; } #mfooter{ color:#666; margin-top: 30px; } #main-content iframe{ margin-bottom: 0; display: inline-block; } .searchBox{ background: #fff !important; }  .download div{ display: none; }  table#searchResult{ margin: 20px auto; max-width: 1000px; } #report{ display: none; } #adblock-tester{ display: none; }.row {display: flex;}#q{width:660px}.browse .col-left, .browse .col-right {width: 140px; margin: 0; padding: 10px;}.ad120, .ad468, .ad728 {display: block;}.browse .col-center {width: 100%;margin: 0; padding: 10px;}.browse {display: flex;flex-direction: row;}.main{width:1024px;margin: 0 auto;}@media only screen and (max-width: 700px) { #categoriesTable{width:100%} \r\n"
			+ "#main-content{margin-left: 0;margin-right: 0;overflow: auto;} #content{min-width: 100%; max-width: 100%;}#details dl{float:none}.nfo{width:94%} #detailsouterframe{width:100%}  }\r\n"
			+ "</style>\r\n"
			+ "<style>table#searchResult {max-width: 1200px;}@media only screen and (max-width: 700px) { #categoriesTable{width:100%} \r\n"
			+ "#main-content{margin-left: 0;margin-right: 0;overflow: auto;} #content{min-width: 100%; max-width: 100%;}#details dl{float:none}.nfo{width:94%} #detailsouterframe{width:100%}  }\r\n"
			+ "</style>\r\n"
			+ "</head>\r\n"
			+ "<body>\r\n"
			+ "<div id=\"header\">\r\n"
			+ "<form method=\"get\" id=\"q\" action=\"/s/\">\r\n"
			+ "<a href=\"/\" class=\"img\"><img src=\"/static/img/tpblogo_sm_ny.gif\" id=\"TPBlogo\" alt=\"The Pirate Bay\" /></a>\r\n"
			+ "<b><a href=\"/\" title=\"Search Torrents\">Search Torrents</a></b>&nbsp;&nbsp;|&nbsp;\r\n"
			+ "<a href=\"/browse\" title=\"Browse Torrents\">Browse Torrents</a>&nbsp;&nbsp;|&nbsp;\r\n"
			+ "<a href=\"/recent\" title=\"Recent Torrents\">Recent Torrents</a>&nbsp;&nbsp;|&nbsp;\r\n"
			+ "<a href=\"/top\" title=\"Top 100\">Top 100</a> <br /><input type=\"search\" title=\"Pirate Search\" name=\"q\" required placeholder=\"Search here...\" value=\"homem aranha\" style=\"background-color:#ffffe0;\" class=\"searchBox\" /><input value=\"Pirate Search\" type=\"submit\" class=\"submitbutton\" /> <br /> <label for=\"audio\" title=\"Audio\"><input id=\"audio\" name=\"audio\" onclick=\"javascript:rmAll();\" type=\"checkbox\" />Audio</label>\r\n"
			+ "<label for=\"video\" title=\"Video\"><input id=\"video\" name=\"video\" onclick=\"javascript:rmAll();\" type=\"checkbox\" checked />Video</label>\r\n"
			+ "<label for=\"apps\" title=\"Applications\"><input id=\"apps\" name=\"apps\" onclick=\"javascript:rmAll();\" type=\"checkbox\" />Applications</label>\r\n"
			+ "<label for=\"games\" title=\"Games\"><input id=\"games\" name=\"games\" onclick=\"javascript:rmAll();\" type=\"checkbox\" />Games</label>\r\n"
			+ "<label for=\"porn\" title=\"Porn\"><input id=\"porn\" name=\"porn\" onclick=\"javascript:rmAll();\" type=\"checkbox\" />Porn</label>\r\n"
			+ "<label for=\"other\" title=\"Other\"><input id=\"other\" name=\"other\" onclick=\"javascript:rmAll();\" type=\"checkbox\" />Other</label>\r\n"
			+ "<select id=\"category\" name=\"category\" onchange=\"javascript:setAll();\">\r\n"
			+ "<option value=\"0\">All</option>\r\n"
			+ "<optgroup label=\"Audio\">\r\n"
			+ "<option value=\"101\">Music</option>\r\n"
			+ "<option value=\"102\">Audio books</option>\r\n"
			+ "<option value=\"103\">Sound clips</option>\r\n"
			+ "<option value=\"104\">FLAC</option>\r\n"
			+ "<option value=\"199\">Other</option>\r\n"
			+ "</optgroup>\r\n"
			+ "<optgroup label=\"Video\">\r\n"
			+ "<option value=\"201\">Movies</option>\r\n"
			+ "<option value=\"202\">Movies DVDR</option>\r\n"
			+ "<option value=\"203\">Music videos</option>\r\n"
			+ "<option value=\"204\">Movie clips</option>\r\n"
			+ "<option value=\"205\">TV shows</option>\r\n"
			+ "<option value=\"206\">Handheld</option>\r\n"
			+ "<option value=\"207\">HD - Movies</option>\r\n"
			+ "<option value=\"208\">HD - TV shows</option>\r\n"
			+ "<option value=\"209\">3D</option>\r\n"
			+ "<option value=\"299\">Other</option>\r\n"
			+ "</optgroup>\r\n"
			+ "<optgroup label=\"Applications\">\r\n"
			+ "<option value=\"301\">Windows</option>\r\n"
			+ "<option value=\"302\">Mac</option>\r\n"
			+ "<option value=\"303\">UNIX</option>\r\n"
			+ "<option value=\"304\">Handheld</option>\r\n"
			+ "<option value=\"305\">IOS (iPad/iPhone)</option>\r\n"
			+ "<option value=\"306\">Android</option>\r\n"
			+ "<option value=\"399\">Other OS</option>\r\n"
			+ "</optgroup>\r\n"
			+ "<optgroup label=\"Games\">\r\n"
			+ "<option value=\"401\">PC</option>\r\n"
			+ "<option value=\"402\">Mac</option>\r\n"
			+ "<option value=\"403\">PSx</option>\r\n"
			+ "<option value=\"404\">XBOX360</option>\r\n"
			+ "<option value=\"405\">Wii</option>\r\n"
			+ "<option value=\"406\">Handheld</option>\r\n"
			+ "<option value=\"407\">IOS (iPad/iPhone)</option>\r\n"
			+ "<option value=\"408\">Android</option>\r\n"
			+ "<option value=\"499\">Other</option>\r\n"
			+ "</optgroup>\r\n"
			+ "<optgroup label=\"Porn\">\r\n"
			+ "<option value=\"501\">Movies</option>\r\n"
			+ "<option value=\"502\">Movies DVDR</option>\r\n"
			+ "<option value=\"503\">Pictures</option>\r\n"
			+ "<option value=\"504\">Games</option>\r\n"
			+ "<option value=\"505\">HD - Movies</option>\r\n"
			+ "<option value=\"506\">Movie clips</option>\r\n"
			+ "<option value=\"599\">Other</option>\r\n"
			+ "</optgroup>\r\n"
			+ "<optgroup label=\"Other\">\r\n"
			+ "<option value=\"601\">E-books</option>\r\n"
			+ "<option value=\"602\">Comics</option>\r\n"
			+ "<option value=\"603\">Pictures</option>\r\n"
			+ "<option value=\"604\">Covers</option>\r\n"
			+ "<option value=\"605\">Physibles</option>\r\n"
			+ "<option value=\"699\">Other</option>\r\n"
			+ "</optgroup>\r\n"
			+ "</select>\r\n"
			+ "</form>\r\n"
			+ "</div>\r\n"
			+ "<h2><span>Search results: homem aranha</span>&nbsp;</h2>\r\n"
			+ "<div id=\"content\">\r\n"
			+ "<div id=\"main-content\">\r\n"
			+ "<table id=\"searchResult\">\r\n"
			+ "<thead id=\"tableHead\">\r\n"
			+ "<tr class=\"header\">\r\n"
			+ "<th>Type</th>\r\n"
			+ "<th>Name</th>\r\n"
			+ "<th><a href=\"/s/0/1/0?q=homem%20aranha&video=on&category=0\" title=\"Order by Uploaded\">Uploaded</a></th> <th>&nbsp;</th>\r\n"
			+ "<th><a href=\"/s/0/3/0?q=homem%20aranha&video=on&category=0\" title=\"Order by Size\">Size</a></th> <th><abbr title=\"Seeders\"><a href=\"/s/0/6/0?q=homem%20aranha&video=on&category=0\" title=\"Order by Seeders\">SE</a></abbr></th>\r\n"
			+ "<th><abbr title=\"Leechers\"><a href=\"/s/0/7/0?q=homem%20aranha&video=on&category=0\" title=\"Order by Leechers\">LE</a></abbr></th> <th><span style=\"white-space: nowrap;\">ULed by</span></th>\r\n"
			+ "</tr>\r\n"
			+ "</thead>\r\n"
			+ "<tbody>\r\n"
			+ "<tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/34785443/Homem_Aranha_Longe_de_Casa_(720p_hd_2019)\" class=\"detLink\" title=\"Details for Homem Aranha Longe de Casa (720p hd-2019)\">Homem Aranha Longe de Casa (720p hd-2019)</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2019-10-02 00:54</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:0FAB8635EC2A225FDF14D3B460E987B05BA5A998&dn=Homem+Aranha+Longe+de+Casa+%28720p+hd-2019%29&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969%2Fannounce&tr=udp%3A%2F%2F9.rarbg.to%3A2920%2Fannounce&tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337&tr=udp%3A%2F%2Ftracker.internetwarriors.net%3A1337%2Fannounce&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.pirateparty.gr%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.cyberia.is%3A6969%2Fannounce\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.63 GiB</td>\r\n"
			+ "<td align=\"right\">31</td>\r\n"
			+ "<td align=\"right\">3</td>\r\n"
			+ "<td><a href=\"/user/zzmax1818\" title=\"Browse zzmax1818\">zzmax1818</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/33938279/Homem_Aranha_Longe_de_Casa_2019\" class=\"detLink\" title=\"Details for Homem Aranha Longe de Casa 2019\">Homem Aranha Longe de Casa 2019</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2019-07-08 00:59</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:D9773385870964F42A1D8B189FE516A1E5C21B92&dn=Homem+Aranha+Longe+de+Casa+2019&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969%2Fannounce&tr=udp%3A%2F%2F9.rarbg.to%3A2920%2Fannounce&tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337&tr=udp%3A%2F%2Ftracker.internetwarriors.net%3A1337%2Fannounce&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.pirateparty.gr%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.cyberia.is%3A6969%2Fannounce\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.62 GiB</td>\r\n"
			+ "<td align=\"right\">23</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td><a href=\"/user/zzmax1818\" title=\"Browse zzmax1818\">zzmax1818</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/31086741/Homem_Aranha_No_Aranhaverso_2019_720p\" class=\"detLink\" title=\"Details for Homem Aranha  No Aranhaverso 2019 720p\">Homem Aranha No Aranhaverso 2019 720p</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2019-04-02 14:49</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:9F88AFFDF7DC46E9636FF7D74DC011188B6E30B1&dn=Homem+Aranha++No+Aranhaverso+2019+720p&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969%2Fannounce&tr=udp%3A%2F%2F9.rarbg.to%3A2920%2Fannounce&tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337&tr=udp%3A%2F%2Ftracker.internetwarriors.net%3A1337%2Fannounce&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.pirateparty.gr%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.cyberia.is%3A6969%2Fannounce\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.78 GiB</td>\r\n"
			+ "<td align=\"right\">19</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td><a href=\"/user/zzmax1818\" title=\"Browse zzmax1818\">zzmax1818</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/10180678/Homem_Aranha_(2002)_1080p_Dublado_Dual_Audio_pt_BR\" class=\"detLink\" title=\"Details for Homem-Aranha (2002) 1080p Dublado / Dual Audio pt-BR\">Homem-Aranha (2002) 1080p Dublado / Dual Audio pt-BR</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-05-19 18:25</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:c60f3b3f17be6b2534cde7a432b8388470230fdd&dn=Homem-Aranha+%282002%29+1080p+Dublado+%2F+Dual+Audio+pt-BR&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">2.03 GiB</td>\r\n"
			+ "<td align=\"right\">17</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/210gustavo\" title=\"Browse 210gustavo\">210gustavo</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/19308193/Homem_Aranha_De_Volta_ao_Lar_2017_BluRay_(720p)_DUBLADO\" class=\"detLink\" title=\"Details for Homem Aranha - De Volta ao Lar 2017 [BluRay] (720p) DUBLADO\">Homem Aranha - De Volta ao Lar 2017 [BluRay] (720p) DUBLADO</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2017-12-07 20:53</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:e3ecf19aff87435da90e1d5e4356dc42228460b3&dn=Homem+Aranha+-+De+Volta+ao+Lar+2017+%5BBluRay%5D+%28720p%29+DUBLADO&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.44 GiB</td>\r\n"
			+ "<td align=\"right\">11</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td><a href=\"/user/drj_tm\" title=\"Browse drj_tm\">drj_tm</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/18467459/Homem_Aranha_De_Volta_ao_Lar_(2017)_720p_DUAL\" class=\"detLink\" title=\"Details for Homem-Aranha De Volta ao Lar (2017) [720p] DUAL -\">Homem-Aranha De Volta ao Lar (2017) [720p] DUAL -</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2017-08-25 07:18</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:3662c9e0eefde903e158d721ce68652be6167e90&dn=Homem-Aranha+De+Volta+ao+Lar+%282017%29+%5B720p%5D+DUAL+-&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.41 GiB</td>\r\n"
			+ "<td align=\"right\">10</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td><a href=\"/user/LAPUMiAFiLMES\" title=\"Browse LAPUMiAFiLMES\">LAPUMiAFiLMES</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/10283751/Homem_Aranha_2_(2004)_1080p_Dublado_Dual_udio_pt_BR\" class=\"detLink\" title=\"Details for Homem-Aranha 2 (2004) 1080p Dublado / Dual Áudio pt-BR\">Homem-Aranha 2 (2004) 1080p Dublado / Dual Áudio pt-BR</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-06-02 23:16</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:3ba9adeed2ee648a31e10ef1a4629823813221cc&dn=Homem-Aranha+2+%282004%29+1080p+Dublado+%2F+Dual+%26Aacute%3Budio+pt-BR&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">2.13 GiB</td>\r\n"
			+ "<td align=\"right\">9</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td><a href=\"/user/210gustavo\" title=\"Browse 210gustavo\">210gustavo</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/10325835/Homem_Aranha_3_(2007)_1080p_Dublado_Dual_Audio_pt_BR\" class=\"detLink\" title=\"Details for Homem-Aranha 3 (2007) 1080p Dublado / Dual Audio pt-BR\">Homem-Aranha 3 (2007) 1080p Dublado / Dual Audio pt-BR</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-06-09 23:33</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:dfa7a0c9aa3082d29d31915af6775693e2e3a5fe&dn=Homem-Aranha+3+%282007%29+1080p+Dublado+%2F+Dual+Audio+pt-BR&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">2.33 GiB</td>\r\n"
			+ "<td align=\"right\">9</td>\r\n"
			+ "<td align=\"right\">2</td>\r\n"
			+ "<td><a href=\"/user/210gustavo\" title=\"Browse 210gustavo\">210gustavo</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/11358257/Homem_Aranha_2_(2004)_BluRay_1080p_Dublado\" class=\"detLink\" title=\"Details for Homem-Aranha 2 (2004) BluRay 1080p Dublado\">Homem-Aranha 2 (2004) BluRay 1080p Dublado</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-11-03 01:29</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:849c7425a3e895c395e5fc25a2dbf10bb990703f&dn=Homem-Aranha+2+%282004%29+BluRay+1080p+Dublado&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">2.5 GiB</td>\r\n"
			+ "<td align=\"right\">4</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/1V4D\" title=\"Browse 1V4D\">1V4D</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/11347859/Homem_Aranha_(2002)_BluRay_1080p_Dublado\" class=\"detLink\" title=\"Details for Homem-Aranha (2002) BluRay 1080p Dublado\">Homem-Aranha (2002) BluRay 1080p Dublado</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-11-01 21:48</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:43776d3e980526882028542724198c76b6b5caaf&dn=Homem-Aranha+%282002%29+BluRay+1080p+Dublado&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.92 GiB</td>\r\n"
			+ "<td align=\"right\">2</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/1V4D\" title=\"Browse 1V4D\">1V4D</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/7486378/O_Espetacular_Homem_Aranha_Dublado_RMVB\" class=\"detLink\" title=\"Details for O Espetacular Homem-Aranha Dublado RMVB\">O Espetacular Homem-Aranha Dublado RMVB</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2012-07-29 23:25</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:2d0477896a11997728e4ae811083b8e2a1cd03e3&dn=O+Espetacular+Homem-Aranha+Dublado+RMVB&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ " </nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">342.39 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/SenhoreS\" title=\"Browse SenhoreS\">SenhoreS</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/7660748/O_Espetacular_Homem_Aranha_2012_R6_Dual_Audio\" class=\"detLink\" title=\"Details for O Espetacular Homem  Aranha 2012.R6 Dual Audio\">O Espetacular Homem Aranha 2012.R6 Dual Audio</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2012-09-22 06:51</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:488db571126fd14240992c20a092f425624d3764&dn=O+Espetacular+Homem++Aranha+2012.R6+Dual+Audio&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.55 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/peixeboi\" title=\"Browse peixeboi\">peixeboi</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/7763441/O_espetacular_homem_aranha_dublado_ptbr_dvdrip_avi\" class=\"detLink\" title=\"Details for O espetacular homem aranha dublado ptbr dvdrip avi\">O espetacular homem aranha dublado ptbr dvdrip avi</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2012-10-27 01:13</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:9a14ce2c4451c539994f3d3bc7dcb894815f289b&dn=O+espetacular+homem+aranha+dublado+ptbr+dvdrip+avi&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">963.38 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/vagnerdj2012\" title=\"Browse vagnerdj2012\">vagnerdj2012</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/7771016/O_Espetacular_Homem_Aranha_BDrip_Dual_Audio\" class=\"detLink\" title=\"Details for O Espetacular Homem Aranha  BDrip  Dual Audio\">O Espetacular Homem Aranha BDrip Dual Audio</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2012-10-29 17:07</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:91e590661e77679afe1637d8151ad7a4baf3f509&dn=O+Espetacular+Homem+Aranha++BDrip++Dual+Audio&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ " <td align=\"right\">949.19 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/tony_hawk\" title=\"Browse tony_hawk\">tony_hawk</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/209\" title=\"More from this category\">Video &gt; 3D</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/7836698/ESPETACULAR_HOMEM_ARANHA\" class=\"detLink\" title=\"Details for ESPETACULAR HOMEM ARANHA\">ESPETACULAR HOMEM ARANHA</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2012-11-18 02:55</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:0e6eeed978a2b26a21fdab4a871dcdb9a47221da&dn=ESPETACULAR+HOMEM+ARANHA&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">25.56 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/RRommel\" title=\"Browse RRommel\">RRommel</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/7857340/o_espetacular_homem_aranha\" class=\"detLink\" title=\"Details for o espetacular homem aranha\">o espetacular homem aranha</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2012-11-25 01:03</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:f791d3915193c8208d8609781efe547276b545e1&dn=o+espetacular+homem+aranha&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">949.19 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/incubus14\" title=\"Browse incubus14\">incubus14</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/7892367/Homem_Aranha_3_Dublado_em_PT_BR\" class=\"detLink\" title=\"Details for Homem Aranha 3 - Dublado em PT-BR\">Homem Aranha 3 - Dublado em PT-BR</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2012-12-06 00:37</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:a22e7e3fe43004c0a1c893f563e043e65bd74a6c&dn=Homem+Aranha+3+-+Dublado+em+PT-BR&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">796.88 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/vini17\" title=\"Browse vini17\">vini17</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/7956940/O_Espetacular_Homem_Aranha\" class=\"detLink\" title=\"Details for O Espetacular Homem-Aranha\">O Espetacular Homem-Aranha</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2012-12-25 17:04</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:25c089bdb988cc84bae9185a4b5b669c1cc5d69a&dn=O+Espetacular+Homem-Aranha&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.34 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/Dr.NunoG\" title=\"Browse Dr.NunoG\">Dr.NunoG</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/8601055/Filme_Demolidor_VS_Homem_Aranha_(Dublado)\" class=\"detLink\" title=\"Details for Filme Demolidor VS Homem Aranha (Dublado)\">Filme Demolidor VS Homem Aranha (Dublado)</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2013-06-22 06:36</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:122bc8f3d96ccc46e812b536ee91bf7778b73498&dn=Filme+Demolidor+VS+Homem+Aranha+%28Dublado%29&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">623.2 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/DuDoG\" title=\"Browse DuDoG\">DuDoG</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/9133805/O_Espetacular_Homem_Aranha_(2012)_1080p_5_1_Dublado_ToTTi9\" class=\"detLink\" title=\"Details for O Espetacular Homem-Aranha (2012) 1080p 5.1 Dublado ToTTi9\">O Espetacular Homem-Aranha (2012) 1080p 5.1 Dublado ToTTi9</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2013-11-02 07:45</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:9b858455464a2fd180579d10091f8b95a8a2cc1f&dn=O+Espetacular+Homem-Aranha+%282012%29+1080p+5.1+Dublado+ToTTi9&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">2.27 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/ToTTi9\" title=\"Browse ToTTi9\">ToTTi9</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/205\" title=\"More from this category\">Video &gt; TV shows</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/9194773/Homem_Aranha_A_o_Sem_Limites_Dublado_Completo\" class=\"detLink\" title=\"Details for Homem Aranha Ação Sem Limites - Dublado - Completo\">Homem Aranha Ação Sem Limites - Dublado - Completo</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2013-11-14 20:54</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:c2d28b1f97a2047084c8153ba37be4ec0ec86d26&dn=Homem+Aranha+A%26ccedil%3B%26atilde%3Bo+Sem+Limites+-+Dublado+-+Completo&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.74 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/reianubiz\" title=\"Browse reianubiz\">reianubiz</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/201\" title=\"More from this category\">Video &gt; Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/9613785/O_Espetacular_Homem_Aranha(The_Amazing_Spider_Man)PTBR_K_A_T_O\" class=\"detLink\" title=\"Details for O Espetacular Homem-Aranha(The Amazing Spider-Man)PTBR_K A T O\">O Espetacular Homem-Aranha(The Amazing Spider-Man)PTBR_K A T O</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-02-14 12:24</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:e0d6d723b1982f62029b3273b9540e42c351e363&dn=O+Espetacular+Homem-Aranha%28The+Amazing+Spider-Man%29PTBR_K+A+T+O&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">2.3 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/Zzagor\" title=\"Browse Zzagor\">Zzagor</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/9910553/O_Espetacular_Homem_Aranha_(2012)_BDRip_720p_dublado\" class=\"detLink\" title=\"Details for O Espetacular Homem Aranha (2012) BDRip 720p dublado\">O Espetacular Homem Aranha (2012) BDRip 720p dublado</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-04-07 16:49</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:1424c17050b2d793f368ca50efb12e74796df73f&dn=O+Espetacular+Homem+Aranha+%282012%29+BDRip+720p+dublado&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">933.15 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/derew\" title=\"Browse derew\">derew</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ " </td>\r\n"
			+ "<td><a href=\"/torrent/10204115/O_Espetacular_Homem_Aranha_(2012)_1080p_Dublado\" class=\"detLink\" title=\"Details for O Espetacular Homem Aranha (2012) 1080p Dublado\">O Espetacular Homem Aranha (2012) 1080p Dublado</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-05-23 03:35</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:8086c8fe9eaf45d238dd0612f4598b7a0d0fdd27&dn=O+Espetacular+Homem+Aranha+%282012%29+1080p+Dublado&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">2.07 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/ramonTPB\" title=\"Browse ramonTPB\">ramonTPB</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/10626630/O_Espetacular_Homem_Aranha_2_(2014)_WEB_DL_720p_Legendado\" class=\"detLink\" title=\"Details for O Espetacular Homem-Aranha 2 (2014) WEB-DL 720p Legendado\">O Espetacular Homem-Aranha 2 (2014) WEB-DL 720p Legendado</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-07-24 23:21</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:b9effdca5453500e6c9f0d0dcfe8640b9530f94e&dn=O+Espetacular+Homem-Aranha+2+%282014%29+WEB-DL+720p+Legendado&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.67 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/wolverdon\" title=\"Browse wolverdon\">wolverdon</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/10678932/O_Espetacular_Homem_Aranha_2_(2014)_720p_Bluray\" class=\"detLink\" title=\"Details for O Espetacular Homem Aranha 2 (2014) 720p Bluray\">O Espetacular Homem Aranha 2 (2014) 720p Bluray</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-08-02 18:55</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:d4ed3184b231e0c09814f91e3dc18141769ee4d0&dn=O+Espetacular+Homem+Aranha+2+%282014%29+720p+Bluray&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">932.76 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/Lucc_S4F\" title=\"Browse Lucc_S4F\">Lucc_S4F</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ " </td>\r\n"
			+ "<td><a href=\"/torrent/10686833/O_Espetacular_Homem_Aranha_2_A_Amea_a_de_Electro_LEGENDAD\" class=\"detLink\" title=\"Details for O Espetacular Homem-Aranha 2 A Ameaça de Electro LEGENDAD\">O Espetacular Homem-Aranha 2 A Ameaça de Electro LEGENDAD</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-08-03 17:04</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:6cbc678072986809b660afbd8f304a0b70a1f08d&dn=O+Espetacular+Homem-Aranha+2+A+Amea%26ccedil%3Ba+de+Electro+LEGENDAD&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">930.69 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/Mateusdeluca\" title=\"Browse Mateusdeluca\">Mateusdeluca</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/10688874/O_Espetacular_Homem_Aranha_2_1080p_Legendado_Matheus\" class=\"detLink\" title=\"Details for O Espetacular Homem-Aranha 2 1080p Legendado - Matheus\">O Espetacular Homem-Aranha 2 1080p Legendado - Matheus</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-08-03 20:04</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:616b61ccf08bd43d2a92472bd1fa1d899a062408&dn=O+Espetacular+Homem-Aranha+2+1080p+Legendado+-+Matheus&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.62 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/matheus2302\" title=\"Browse matheus2302\">matheus2302</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr>\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/207\" title=\"More from this category\">Video &gt; HD - Movies</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/10819931/O_Espetacular_Homem_Aranha_2_(2014)_720p_Dublado\" class=\"detLink\" title=\"Details for O Espetacular Homem Aranha 2 (2014) 720p Dublado\">O Espetacular Homem Aranha 2 (2014) 720p Dublado</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-08-20 04:00</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:08fd379b4df90920eef5008b41d82f8700e07747&dn=O+Espetacular+Homem+Aranha+2+%282014%29+720p+Dublado&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">977.86 MiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/andreTPF\" title=\"Browse andreTPF\">andreTPF</a>\r\n"
			+ "</td>\r\n"
			+ "</tr><tr class=\"alt\">\r\n"
			+ "<td class=\"vertTh\"><a href=\"/browse/208\" title=\"More from this category\">Video &gt; HD - TV shows</a>\r\n"
			+ "</td>\r\n"
			+ "<td><a href=\"/torrent/10834448/O_Espetacular_Homem_Aranha_2_A_Amea_a_De_Electro_(2014)\" class=\"detLink\" title=\"Details for O Espetacular Homem-Aranha 2  A Ameaça De Electro (2014)\">O Espetacular Homem-Aranha 2 A Ameaça De Electro (2014)</a>\r\n"
			+ "</td>\r\n"
			+ "<td>2014-08-21 22:38</td>\r\n"
			+ "<td>\r\n"
			+ "<nobr>\r\n"
			+ "<a href=\"magnet:?xt=urn:btih:646740aa122971e35ab3e00d576b14c45a8cbaca&dn=O+Espetacular+Homem-Aranha+2++A+Amea%26ccedil%3Ba+De+Electro+%282014%29&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969\" title=\"Download this torrent using magnet\" style=\"display:none\">\r\n"
			+ "<img src=\"/static/img/icon-magnet.gif\" alt=\"Magnet link\">\r\n"
			+ "</a>\r\n"
			+ "</nobr>\r\n"
			+ "</td>\r\n"
			+ "<td align=\"right\">1.29 GiB</td>\r\n"
			+ "<td align=\"right\">1</td>\r\n"
			+ "<td align=\"right\">0</td>\r\n"
			+ "<td><a href=\"/user/Nroberto\" title=\"Browse Nroberto\">Nroberto</a>\r\n"
			+ "</td>\r\n"
			+ "</tr> <tr><td colspan=\"9\" style=\"text-align:center;\"> <b>1</b>&nbsp;<a href=\"/s/page/2/?q=homem%20aranha&video=on&category=0\">2</a>&nbsp;<a href=\"/s/page/3/?q=homem%20aranha&video=on&category=0\">3</a>&nbsp;<a href=\"/s/page/4/?q=homem%20aranha&video=on&category=0\">4</a>&nbsp; <a href=\"/s/page/2/?q=homem%20aranha&video=on&category=0\"><img src=\"/static/img/next.gif\" border=\"0\" alt=\"Next\"></a>&nbsp; </td></tr> </tbody>\r\n"
			+ "</table>\r\n"
			+ "</div>\r\n"
			+ "</div>\r\n"
			+ "<div id=\"foot\" style=\"text-align:center;margin-top:1em;\">\r\n"
			+ "<p>\r\n"
			+ "<a href=\"/register/\" title=\"Login\">Login</a> |\r\n"
			+ "<a href=\"/register\" title=\"Register\">Register</a> |\r\n"
			+ "<a href=\"/about\" title=\"About\">About</a> |\r\n"
			+ "<a target=\"_blank\" href=\"https://the-piratebay.com\" title=\"The Pirate Bay\">PirateBay</a>\r\n"
			+ "<br>\r\n"
			+ "\r\n"
			+ "<a target=\"_blank\" href=\"https://the-piratebay.com\" title=\"thepiratebay\">ThePirateBay</a> |\r\n"
			+ "<a href=\"https://www.rarbggo.to\" title=\"rarbg\">RARBG</a> |\r\n"
			+ "<br>\r\n"
			+ "</p>\r\n"
			+ "</div></block>\r\n"
			+ "<script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script><script>var year = $('.upyear').attr('data');if(year<2020){$('.download').hide();}</script><div style=\"text-align:center;\"><div style=\"display:none\"><div id=\"histats_counter\"></div>\r\n"
			+ "\r\n"
			+ "<script type=\"text/javascript\">var _Hasync= _Hasync|| [];\r\n"
			+ "_Hasync.push(['Histats.start', '1,3375665,4,511,95,18,00000000']);\r\n"
			+ "_Hasync.push(['Histats.fasi', '1']);\r\n"
			+ "_Hasync.push(['Histats.track_hits', '']);\r\n"
			+ "(function() {\r\n"
			+ "var hs = document.createElement('script'); hs.type = 'text/javascript'; hs.async = true;\r\n"
			+ "hs.src = ('//s10.histats.com/js15_as.js');\r\n"
			+ "(document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(hs);\r\n"
			+ "})();</script>\r\n"
			+ "<noscript><a href=\"/\" target=\"_blank\"><img  src=\"//sstatic1.histats.com/0.gif?3375665&101\" alt=\"counter easy hit\" border=\"0\"></a></noscript>\r\n"
			+ "</div>\r\n"
			+ "<script type=\"text/javascript\" src=\"//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-63732f92550dd7f9\"></script></div></body>\r\n"
			+ "</html>"; 
	 */

}
