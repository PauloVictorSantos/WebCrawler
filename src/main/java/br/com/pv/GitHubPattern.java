package br.com.pv;

import java.io.Serializable;

public class GitHubPattern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String REPOSITORIES_TAB = 	"[^<a\\shref=\"].*?\\?tab=repositories";
	// Expressao usada para recuperar foto do usuario.

	public static final String AVATARED = 
			"<img\\sclass=\"avatar(.*?)\"[^>]*?\\ssrc=\"(.*?)\" width=\"(.*?)\" height=\"(.*?)\">";
	// Expressao usada para recuperar lista de repositorios.

	public static final String REPOSITORIES_LIST = "<h3>[^>]*?<\\s*a\\s+[^>]*href\\s*=\\s*[\"']?([^\"' >]+)[\"' >]";
	// Expressao usada para recuperar arquivo zip para download.

	public static final String DOWNLOAD_ZIP ="<a\\shref=\"(.*?)\"[^>]*?>[^>]*?</a>";
			// Expressao usada para recuperar links para ocomando git clone.

	public static final String CLONE_URL = "<input[^>]*? value=\"(.*?)\" readonly=''>";
	// Expressao usada para recuperar menu principal da home do github.

	public static final String GITHUB_HOME = "<input[^>]*?\\sclass=\"(.*?)header-search-input(.*?)\">";

	// Expressao usada para recuperar erro caso usuario submetido nao exista
}
