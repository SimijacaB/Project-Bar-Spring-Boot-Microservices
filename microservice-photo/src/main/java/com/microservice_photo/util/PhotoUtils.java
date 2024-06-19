package com.microservice_photo.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class PhotoUtils {
    public static byte[] compressImage(byte[] data) {
        //Deflater es una clase que se utiliza para comprimir datos, funciona de la siguiente manera:
        //1. Se crea una instancia de la clase Deflater
        //2. Se establece el nivel de compresión
        //3. Se establece el input (los datos a comprimir)
        //4. Se llama al método finish() para indicar que se han terminado de ingresar los datos
        //5. Se llama al método deflate() para comprimir los datos
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        //ByteArrayOutputStream es una clase que se utiliza para escribir datos en un array de bytes y funciona de la siguiente manera:
        //1. Se crea una instancia de la clase ByteArrayOutputStream
        //2. Se llama al método write() para escribir los datos en el array de bytes
        //3. Se llama al método close() para cerrar el stream y liberar los recursos
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {

            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        //Inflater es una clase que se utiliza para descomprimir datos, funciona de la siguiente manera:
        //1. Se crea una instancia de la clase Inflater
        //2. Se establece el input (los datos a descomprimir)
        //3. Se crea una instancia de ByteArrayOutputStream para almacenar los datos descomprimidos
        //4. Se llama al método inflate() para descomprimir los datos
        //5. Se cierra el stream y se obtienen los datos descomprimidos en un array de bytes
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
}
