package com.webscraping.classes

class Componentes {
    String competencia, publicacao, inicio_vigencia

    Componentes(String competencia, String publicacao, String inicio_vigencia) {
        this.competencia = competencia
        this.publicacao = publicacao
        this.inicio_vigencia = inicio_vigencia
    }

    String getCompetencia() {
        return competencia
    }

    void setCompetencia(String competencia) {
        this.competencia = competencia
    }

    String getPublicacao() {
        return publicacao
    }

    void setPublicacao(String publicacao) {
        this.publicacao = publicacao
    }

    String getInicio_vigencia() {
        return inicio_vigencia
    }

    void setInicio_vigencia(String inicio_vigencia) {
        this.inicio_vigencia = inicio_vigencia
    }


    @Override
    public String toString() {
        return "com.webscraping.classes.Componentes{" +
                "competencia='" + competencia + '\'' +
                ", publicacao='" + publicacao + '\'' +
                ", inicio_vigencia='" + inicio_vigencia + '\'' +
                '}';
    }
}
