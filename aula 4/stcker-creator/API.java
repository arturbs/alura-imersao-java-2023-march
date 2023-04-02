public enum API {

    //mudar para url validas
    IMDB_TOP_MOVIES("https://imdb-api.com/en/API/Top250Movies/k_x3pev8lm", new ExtratorDeConteudoDoIMDB()),
    IMDB_TOP_SERIES("https://imdb-api.com/en/API/Top250TVs/k_x3pev8lm", new ExtratorDeConteudoDoIMDB()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=mts2vT3l0bbKT46DDwROPWX0XfLXZJWdycrnAWTm&start_date=2023-01-10&end_date=2023-01-14", new ExtratorDeConteudoDaNasa());

    private String url;
    private ExtratorDeConteudo extrator;
    
    API(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }
}
