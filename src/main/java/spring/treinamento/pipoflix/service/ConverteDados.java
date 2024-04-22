package spring.treinamento.pipoflix.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    //Essa classe foi criada para fazer a conversão de dados.
    //no Gson usamos o fromJson e toJson. Para fazer isso no Jackson, precisamos de uma classe chamada ObjectMapper.
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



}
