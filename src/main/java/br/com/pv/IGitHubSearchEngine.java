package br.com.pv;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;

public interface IGitHubSearchEngine {
	final String DOMAIN = "https://github.com";

	// Clausula para executar de pesquisa padrao.
	GitHub search(HttpClient httpclient, HttpGet httpget, ResponseHandler<String> responseHandler);

	// Clausula para buscar foto de usuario github.
	String findUrlAvatared(HttpClient httpclient, HttpGet httpget, ResponseHandler<String> responseHandler);

	// Clausula para buscar url parent da busca derepositorios

	String findUrlTabRepositories(HttpClient httpclient, HttpGet httpget, ResponseHandler<String> responseHandler);

	// Clausula para buscar url de arquivo ZIP de repositorio.
	String downloadZipFile(HttpClient httpclient, HttpGet httpget, ResponseHandler<String>responseHandler);

	// Clausula para buscar links para comandos git clone.
	List<GitHubCloneUrl> findCloneUrls(HttpClient httpclient, HttpGet httpget, ResponseHandler<String> responseHandler);

	// Clausula para buscar links da pagina principal do github.
	List<GitHubHome> findMenuGitHubHome(HttpClient httpclient, HttpGet httpget,
			ResponseHandler<String> responseHandler);
}