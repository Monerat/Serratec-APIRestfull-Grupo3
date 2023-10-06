package br.com.grupo3.futebol.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.grupo3.futebol.model.Time;
import br.com.grupo3.futebol.model.exceptions.ResourceNotFound;

@Repository
public class TimeRepository {
    private List<Time> times = new ArrayList<>();
    private long ultimoID = 0;

    // Crud
    // Create
    public Time add(Time time) {
        ultimoID++;
        time.setId(ultimoID);

        try {
            time.setEscudo(verifEscudo(time.getNome() + ".png"));
        } catch (Exception e) {
            time.setBase64img(verifEscudo(time.getNome() + ".txt"));
        }

        times.add(time);

        return time;
    }

    public String verifEscudo(String fileName) {
        String folderPath = "src/main/java/br/com/grupo3/futebol/base/";
        File file = new File(folderPath + fileName);

        if (file.exists()) {
            return file.getAbsolutePath();

            // EU ME PERDI LEGAL NISSO AQUI ME DESCULPA PERDI MUITO TEMPO NISSO QUIS FAZER
            // FUNCIONAR PEÇO PERDÃO PELO VACILO
            // MAS FUNCIONA ... EU ACHO :)
            // EDIT: NAO FUNCIONOU, NAO SALVA O TIME NA API, APENAS ADICIONA O TXT (:
        } else {
            // Se o arquivo não existir, tenta criar um novo arquivo txt com a imagem em
            // base64, ai nao precisa existir o arquivo .txt
            if (fileName.endsWith(".txt")) {
                try {
                    String imageName = fileName.substring(0, fileName.length() - 4); // Remove a extensão .txt
                    File imageFile = new File(folderPath + imageName);
                    if (imageFile.exists()) {

                        // Codifica a imagem em base64
                        byte[] fileContent = Files.readAllBytes(imageFile.toPath());
                        String encodedString = Base64.getEncoder().encodeToString(fileContent);

                        // Salva a string codificada em um novo arquivo txt
                        Files.write(file.toPath(), encodedString.getBytes(StandardCharsets.UTF_8));

                        return file.getAbsolutePath();
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao criar o arquivo txt com a imagem em base64", e);
                }
            }

            throw new ResourceNotFound("Arquivo não encontrado na base com o nome: " + fileName);
        }
    }

    // Read
    // Read all
    public List<Time> getAll() {
        return times;
    }

    // Read id
    public Time get(long id) {
        Time timeFound = null;
        for (Time time : times) {
            if (time.getId() == id) {
                timeFound = time;
            }
        }
        return timeFound;
    }

    // Update
    public Time update(Time time) {
        
        time.setEscudo(verifEscudo(time.getNome() + ".png"));
        times.removeIf(t -> t.getId() == time.getId());
        time.setEscudo(verifEscudo(time.getNome() + ".png"));
        times.add(time);

        return time;
    }

    // Delete
    public void delete(long id) {
        times.removeIf(t -> t.getId() == id);
    }
}