package br.com.pv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public final class GitHubSearchEngine implements IGitHubSearchEngine {
	// Referencias para motor de busca
	private ICompileRegex regex;
	private Matcher matcher;
	// Variaveis de instancia
	private String urlTabRepositories;
	private String urlAvatared;
	private String urlDownloadZipFile;
	private List<GitHubRepository> listGitHubRepository;
	private List<GitHubCloneUrl> listGitHubCloneUrl;
	private final GitHub gitHub;
	private List<GitHubHome> listGitHubHome;

	// Construtor com argumento
	public GitHubSearchEngine(ICompileRegex regex) {
		this.regex = regex;
		this.gitHub = new GitHub();
	}

	// outros métodos...
	/**
	 * Metodo utilizado para recuperar lista de links da home do github.
	 */
	public List<GitHubHome> findMenuGitHubHome(HttpClient httpclient, HttpGet httpget,
			ResponseHandler<String> responseHandler) {
		try {
			// Cria referencia para lista de objetos GitHubHome.
			this.listGitHubHome = new ArrayList<GitHubHome>();
			// Executa regex na resposta do httpclient.
			this.matcher = regex.execute(GitHubPattern.GITHUB_HOME, httpclient.execute(httpget, responseHandler));
			/*
			 * Percorre casamento do padrao, cria uma referencia de GitHubHome,
			 * populando e adicionando lista listGitHubCloneUrl
			 */
			while (this.matcher.find()) {
				// Cria referencia do Bean de menu da home.
				GitHubHome gitHubHome = new GitHubHome();
				// Seta Valores para a referencia do bean.
				gitHubHome.setUrl(this.matcher.group(1));
				gitHubHome.setTitle(this.matcher.group(2));
				// Adiciona a referencia atual a lista de itens de menu.
				this.listGitHubHome.add(gitHubHome);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Fecha a conexao de httpclient.
			httpclient.getConnectionManager().shutdown();
		}

		return this.listGitHubHome;
	}
	// Outros médotos...
	public GitHub search(HttpClient httpclient, HttpGet httpget, ResponseHandler<String> responseHandler) {

		try {

			this.gitHub.setUrlAvatared(new GitHubSearchEngine(this.regex).findUrlAvatared(httpclient, httpget, responseHandler));

			this.gitHub.setUrlTabRepositories(new GitHubSearchEngine(this.regex).findUrlTabRepositories(httpclient, httpget, responseHandler));

			if (this.gitHub.getUrlTabRepositories() != null) {

				httpget = new HttpGet(this.gitHub.getUrlTabRepositories());

			}

			this.matcher = this.regex.execute(GitHubPattern.REPOSITORIES_LIST, httpclient.execute(httpget, responseHandler));
			
			this.listGitHubRepository = new ArrayList();

			while (this.matcher.find()) {

				GitHubRepository gitHubRepository = new GitHubRepository();

				gitHubRepository.setUrl("https://github.com"
						+ this.matcher.group(1));

				gitHubRepository.setTitle(this.matcher.group(2));
				System.out.print(this.matcher.group(2));
				gitHubRepository.setZip(new GitHubSearchEngine(this.regex)
						.downloadZipFile(new DefaultHttpClient(), new HttpGet(
								"https://github.com" + this.matcher.group(1)),
								new BasicResponseHandler()));

				this.listGitHubRepository.add(gitHubRepository);

				this.gitHub.setCloneUrls(new GitHubSearchEngine(this.regex)
						.findCloneUrls(new DefaultHttpClient(), new HttpGet(
								"https://github.com" + this.matcher.group(1)),
								new BasicResponseHandler()));
			}

			this.gitHub.setRepositories(this.listGitHubRepository);

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		finally {

			httpclient.getConnectionManager().shutdown();

		}

		return this.gitHub;

	}

	public String findUrlAvatared(HttpClient httpclient, HttpGet httpget,
			ResponseHandler<String> responseHandler) {

		try {

			this.matcher = this.regex.execute(GitHubPattern.AVATARED,
					(String) httpclient.execute(httpget, responseHandler));

			while (this.matcher.find()) {
				this.urlAvatared = this.matcher.group(1);
			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return this.urlAvatared;
	}

	public String findUrlTabRepositories(HttpClient httpclient,
			HttpGet httpget, ResponseHandler<String> responseHandler) {

		try {

			this.matcher = this.regex.execute(GitHubPattern.REPOSITORIES_TAB,
					(String) httpclient.execute(httpget, responseHandler));

			while (this.matcher.find()) {
				this.urlTabRepositories = ("https://github.com" + this.matcher
						.group(0));
			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return this.urlTabRepositories;

	}

	public String downloadZipFile(HttpClient httpclient, HttpGet httpget,
			ResponseHandler<String> responseHandler) {

		try {

			this.matcher = this.regex.execute(GitHubPattern.DOWNLOAD_ZIP,
					(String) httpclient.execute(httpget, responseHandler));

			while (this.matcher.find()) {
				this.urlDownloadZipFile = ("https://github.com" + this.matcher.group(1));
			}

		}

		catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return this.urlDownloadZipFile;
	}

	public List<GitHubCloneUrl> findCloneUrls(HttpClient httpclient,
			HttpGet httpget, ResponseHandler<String> responseHandler) {

		try {

			this.listGitHubCloneUrl = new ArrayList();

			this.matcher = this.regex
					.execute(GitHubPattern.CLONE_URL,
							(String) httpclient.execute(httpget, responseHandler));

			while (this.matcher.find()) {

				GitHubCloneUrl gitHubCloneUrl = new GitHubCloneUrl();

				gitHubCloneUrl.setUrl(this.matcher.group(1));

				gitHubCloneUrl.setTitle(this.matcher.group(2));

				this.listGitHubCloneUrl.add(gitHubCloneUrl);

			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return this.listGitHubCloneUrl;

	}

}
