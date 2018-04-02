package br.com.pv;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

@Path("/github")
public final class GitHubService {
	/**
	 * Metodo do servico que retorna links da pagina principal do github.com
	 * ACESSAR DA SEGUINTE FORMA: http:// localhost:8080/crawler/service/github/
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, List<GitHubHome>> findMenuHome() {
		// Dominio do github.
		String gitHubHomeDomain = IGitHubSearchEngine.DOMAIN;
		// Cria referencia para map de objetos GitHubHome.
		Map<String, List<GitHubHome>> menuHome = new HashMap<String, List<GitHubHome>>();
		// Cria referencia para GitHubSearchEngine.
		IGitHubSearchEngine gitHub = new GitHubSearchEngine(new CompileRegex());
		/*
		 * Cria referencia listGitHubHome para lista de objetos GitHubHome com o
		 * retorno de gitHub.findMenuGitHubHome().
		 */
		List<GitHubHome> listGitHubHome = gitHub.findMenuGitHubHome(new DefaultHttpClient(),
				new HttpGet(gitHubHomeDomain), new BasicResponseHandler());
		// Adiciona valor ao map.
		menuHome.put(gitHubHomeDomain, listGitHubHome);
		return menuHome;
	}
	
	@GET
	@Produces({ "application/json" })
	@Path("{user}")
	public Map<String, GitHub> findDataUser(@PathParam("user") String user) {
		String gitHubDomainAndUser = "https://github.com/" + user;

		Map repositories = new HashMap();

		IGitHubSearchEngine gitHub = new GitHubSearchEngine(new CompileRegex());

		GitHub gitHubReference = gitHub.search(new DefaultHttpClient(), new HttpGet(gitHubDomainAndUser), new BasicResponseHandler());

		repositories.put(gitHubDomainAndUser, gitHubReference);

		return repositories;
	}
	
}