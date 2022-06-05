

/**
        * Copyright 2014 KC Ochibili
        *
        * Licenciado sob a Licença Apache, Versão 2.0 (a "Licença");
        * você não pode usar este arquivo, exceto em conformidade com a Licença.
        * Você pode obter uma cópia da Licença em
        *
        * http://www.apache.org/licenses/LICENSE-2.0
        *
        * A menos que exigido pela lei aplicável ou acordado por escrito, software
        * distribuído sob a Licença é distribuído "COMO ESTÁ",
        * SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, expressas ou implícitas.
        * Consulte a Licença para as permissões específicas que regem o idioma e
        * limitações sob a Licença.
        * /

        / *
        * O caractere "‚ ‗ ‚" não é uma vírgula, é a MARCA DE CITAÇÃO ÚNICA LOW-9 unicode 201A
        * e Unicode 2017 que são usados para separar os itens em uma lista.
 */


package com.geroncio.foodtruck.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;


import com.geroncio.foodtruck.Domain.FoodDomain;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TinyDB {

    private SharedPreferences preferences;
    private String DEFAULT_APP_IMAGEDATA_DIRECTORY;
    private String lastImagePath = "";

    public TinyDB(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    /**
     * Decodifica o bitmap do 'caminho' e o retorna
      * @parametro path caminho da imagem
      * @retornar o bitmap do 'caminho'
     */

        public Bitmap getImage(String path) {
            Bitmap bitmapFromPath = null;
            try {
                bitmapFromPath = BitmapFactory.decodeFile(path);

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            return bitmapFromPath;
        }

     /**
      * Retorna o caminho da string da última imagem salva
      * @return o caminho da string da última imagem salva
      */

        public String getSavedImagePath() {
            return lastImagePath;
        }

      /**
       * Salva 'theBitmap' na pasta 'theFolder' com o nome 'theImageName'
       * @param theFolder o diretório do caminho da pasta em que deseja salvá-lo, por exemplo, "DropBox / WorkImages"* @param theImageName o nome que você deseja atribuir ao arquivo de imagem, por exemplo, "MeAtLunch.png"
       * @param theBitmap a imagem que você deseja salvar como um bitmap
       * @return retorna o caminho completo (endereço do sistema de arquivos) da imagem salva
       */

          public String putImage(String theFolder, String theImageName, Bitmap theBitmap) {
              if (theFolder == null || theImageName == null || theBitmap == null)
                  return null;

              this.DEFAULT_APP_IMAGEDATA_DIRECTORY = theFolder;
              String mFullPath = setupFullPath(theImageName);

              if (!mFullPath.equals("")) {
                  lastImagePath = mFullPath;
                  saveBitmap(mFullPath, theBitmap);
              }

              return mFullPath;
          }

    /**
     * Salva 'theBitmap' em 'fullPath'
     * @param fullPath caminho completo do arquivo de imagem, por exemplo "Images / MeAtLunch.png"
     * @param theBitmap a imagem que você deseja salvar como um bitmap
     * @return true se a imagem foi salva, false caso contrário
     */

        public boolean putImageWithFullPath(String fullPath, Bitmap theBitmap) {
            return !(fullPath == null || theBitmap == null) && saveBitmap(fullPath, theBitmap);
        }

     /**
      * Cria o caminho para a imagem com o nome 'imageName' no diretório DEFAULT_APP ..
      * @param imageName nome da imagem
      * @return o caminho completo da imagem. Se não conseguiu criar o diretório, retorna uma string vazia
      */

         private String setupFullPath(String imageName) {
             File mFolder = new File(Environment.getExternalStorageDirectory(), DEFAULT_APP_IMAGEDATA_DIRECTORY);

             if (isExternalStorageReadable() && isExternalStorageWritable() && !mFolder.exists()) {
                 if (!mFolder.mkdirs()) {
                     Log.e("ERROR", "Failed to setup folder");
                     return "";
                 }
             }

             return mFolder.getPath() + '/' + imageName;
         }

       /**
        * Salva o bitmap como um arquivo PNG no caminho 'fullPath'
        * @param fullPath caminho do arquivo de imagem
        * @param bitmap a imagem como um bitmap
        * @return true se for salvo com sucesso, false caso contrário
        */

           private boolean saveBitmap(String fullPath, Bitmap bitmap) {
               if (fullPath == null || bitmap == null)
                   return false;

               boolean fileCreated = false;
               boolean bitmapCompressed = false;
               boolean streamClosed = false;

               File imageFile = new File(fullPath);

               if (imageFile.exists())
                   if (!imageFile.delete())
                       return false;

               try {
                   fileCreated = imageFile.createNewFile();

               } catch (IOException e) {
                   e.printStackTrace();
               }

               FileOutputStream out = null;
               try {
                   out = new FileOutputStream(imageFile);
                   bitmapCompressed = bitmap.compress(CompressFormat.PNG, 100, out);

               } catch (Exception e) {
                   e.printStackTrace();
                   bitmapCompressed = false;

               } finally {
                   if (out != null) {
                       try {
                           out.flush();
                           out.close();
                           streamClosed = true;

                       } catch (IOException e) {
                           e.printStackTrace();
                           streamClosed = false;
                       }
                   }
               }

               return (fileCreated && bitmapCompressed && streamClosed);
           }


     //TODO: Metodos Getters (pegar)

     /**
      * Obtenha o valor int de SharedPreferences em 'key'. Se a chave não for encontrada, retorna 0
      * @param key SharedPreferences key
      * @return int value at 'key' ou 0 se a chave não for encontrada
      */

         public int getInt(String key) {
             return preferences.getInt(key, 0);
         }

      /**
       * Obtenha a ArrayList de inteiros analisada em SharedPreferences em 'key'
       * @param key SharedPreferences key
      * @return ArrayList of Integers
      */

          public ArrayList<Integer> getListInt(String key) {
              String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
              ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
              ArrayList<Integer> newList = new ArrayList<Integer>();

              for (String item : arrayToList)
                  newList.add(Integer.parseInt(item));

              return newList;
          }

      /**
       * Obtenha o valor long de SharedPreferences em 'key'. Se a chave não for encontrada, retorna 0
       * @param key SharedPreferences key
       * @return long value at 'key' ou 0 se a chave não for encontrada
       */

          public long getLong(String key) {
              return preferences.getLong(key, 0);
          }

       /**
        * Obtenha o valor flutuante de SharedPreferences em 'key'. Se a chave não for encontrada, retorna 0
        * @param key SharedPreferences key
        * @return valor flutuante na 'chave' ou 0 se a chave não for encontrada
        */

           public float getFloat(String key) {
               return preferences.getFloat(key, 0);
           }

       /**
        * Obtenha o valor double de SharedPreferences em 'key'. Se uma exceção for lançada, retorna 0
        * @param key SharedPreferences key
        * @return double value at 'key' ou 0 se a exceção for lançada
        */

           public double getDouble(String key) {
               String number = getString(key);

               try {
                   return Double.parseDouble(number);

               } catch (NumberFormatException e) {
                   return 0;
               }
           }

        /**
         * Obtenha a ArrayList de Double analisada em SharedPreferences em 'key'
         * @param key SharedPreferences key
         * @return ArrayList of Double
         */

            public ArrayList<Double> getListDouble(String key) {
                String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
                ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
                ArrayList<Double> newList = new ArrayList<Double>();

                for (String item : arrayToList)
                    newList.add(Double.parseDouble(item));

                return newList;
            }

        /**
         * Obtenha a ArrayList de inteiros analisada em SharedPreferences em 'key'
         * @param key SharedPreferences key
         * @return ArrayList of Longs
         */

            public ArrayList<Long> getListLong(String key) {
                String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
                ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
                ArrayList<Long> newList = new ArrayList<Long>();

                for (String item : arrayToList)
                    newList.add(Long.parseLong(item));

                return newList;
            }

        /**
         * Obtenha o valor String de SharedPreferences em 'key'. Se a chave não for encontrada, retorne ""
         * @param key SharedPreferences key
         * @return String valor em 'key' ou "" (String vazia) se a chave não for encontrada
         */

            public String getString(String key) {
                return preferences.getString(key, "");
            }

        /**
         * Obtenha a ArrayList de String analisada em SharedPreferences em 'key'
         * @param key SharedPreferences key
         * @return ArrayList of String
         */

            public ArrayList<String> getListString(String key) {
                return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
            }

        /**
         * Obtenha o valor booleano de SharedPreferences em 'key'. Se a chave não for encontrada, retorna falso
         * @param key SharedPreferences key
         * @return valor booleano na 'chave' ou falso se a chave não for encontrada
         */

            public boolean getBoolean(String key) {
                return preferences.getBoolean(key, false);
            }

        /**
         * Obtenha ArrayList analisado de Boolean de SharedPreferences em 'key'
         * @param key SharedPreferences key
         * @return ArrayList de Boolean
         */

            public ArrayList<Boolean> getListBoolean(String key) {
                ArrayList<String> myList = getListString(key);
                ArrayList<Boolean> newList = new ArrayList<Boolean>();

                for (String item : myList) {
                    if (item.equals("true")) {
                        newList.add(true);
                    } else {
                        newList.add(false);
                    }
                }

                return newList;
            }

            public ArrayList<FoodDomain> getListObject(String key){
                Gson gson = new Gson();

                ArrayList<String> objStrings = getListString(key);
                ArrayList<FoodDomain> playerList =  new ArrayList<FoodDomain>();

                for(String jObjString : objStrings){
                    FoodDomain player  = gson.fromJson(jObjString,  FoodDomain.class);
                    playerList.add(player);
                }
                return playerList;
            }



            public <T> T getObject(String key, Class<T> classOfT){

                String json = getString(key);
                Object value = new Gson().fromJson(json, classOfT);
                if (value == null)
                    throw new NullPointerException();
                return (T)value;
            }


        //TODO: Put methods

        /**
         * Coloque o valor int em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param value int valor a ser adicionado
         */

            public void putInt(String key, int value) {
                checkForNullKey(key);
                preferences.edit().putInt(key, value).apply();
            }

        /**
         * Coloque ArrayList of Integer em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param intList ArrayList of Integer a ser adicionado
         */

            public void putListInt(String key, ArrayList<Integer> intList) {
                checkForNullKey(key);
                Integer[] myIntList = intList.toArray(new Integer[intList.size()]);
                preferences.edit().putString(key, TextUtils.join("‚‗‚", myIntList)).apply();
            }

        /**
         * Coloque um valor long em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param value valor long a ser adicionado
         */


            public void putLong(String key, long value) {
                checkForNullKey(key);
                preferences.edit().putLong(key, value).apply();
            }

        /**
         * Coloque ArrayList of Long em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param longList ArrayList de Long a ser adicionado
         */

            public void putListLong(String key, ArrayList<Long> longList) {
                checkForNullKey(key);
                Long[] myLongList = longList.toArray(new Long[longList.size()]);
                preferences.edit().putString(key, TextUtils.join("‚‗‚", myLongList)).apply();
            }

        /**
         * Coloque o valor float em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param value float value a ser adicionado
         */

            public void putFloat(String key, float value) {
                checkForNullKey(key);
                preferences.edit().putFloat(key, value).apply();
            }

        /**
         * Coloque o valor double em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param value double value a ser adicionado
         */

            public void putDouble(String key, double value) {
                checkForNullKey(key);
                putString(key, String.valueOf(value));
            }

        /**
         * Coloque ArrayList of Double em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param doubleList ArrayList of Double a ser adicionado
         */

            public void putListDouble(String key, ArrayList<Double> doubleList) {
                checkForNullKey(key);
                Double[] myDoubleList = doubleList.toArray(new Double[doubleList.size()]);
                preferences.edit().putString(key, TextUtils.join("‚‗‚", myDoubleList)).apply();
            }

        /**
         * Coloque o valor String em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param value String valor a ser adicionado
         */

            public void putString(String key, String value) {
                checkForNullKey(key); checkForNullValue(value);
                preferences.edit().putString(key, value).apply();
            }

        /**
         * Coloque ArrayList of String em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param stringList ArrayList of String a ser adicionado
         */

            public void putListString(String key, ArrayList<String> stringList) {
                checkForNullKey(key);
                String[] myStringList = stringList.toArray(new String[stringList.size()]);
                preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
            }

        /**
         * Coloque o valor boolean em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param value valor booleano a ser adicionado
         */

            public void putBoolean(String key, boolean value) {
                checkForNullKey(key);
                preferences.edit().putBoolean(key, value).apply();
            }

        /**
         * Coloque ArrayList of Boolean em SharedPreferences com 'key' e salve
         * @param key SharedPreferences key
         * @param boolList ArrayList de Boolean a ser adicionado
         */

            public void putListBoolean(String key, ArrayList<Boolean> boolList) {
                checkForNullKey(key);
                ArrayList<String> newList = new ArrayList<String>();

                for (Boolean item : boolList) {
                    if (item) {
                        newList.add("true");
                    } else {
                        newList.add("false");
                    }
                }

                putListString(key, newList);
            }

        /**
         * Coloque ObJect qualquer tipo em SharedPrefrences com 'key' e salve
         * @param key SharedPreferences key
         * @param obj é o objeto que você deseja colocar
         */

            public void putObject(String key, Object obj){
                checkForNullKey(key);
                Gson gson = new Gson();
                putString(key, gson.toJson(obj));
            }

            public void putListObject(String key, ArrayList<FoodDomain> playerList){
                checkForNullKey(key);
                Gson gson = new Gson();
                ArrayList<String> objStrings = new ArrayList<String>();
                for(FoodDomain player: playerList){
                    objStrings.add(gson.toJson(player));
                }
                putListString(key, objStrings);
            }

        /**
         * Remova o item SharedPreferences com 'chave'
         * @param key SharedPreferences key
         */

            public void remove(String key) {
                preferences.edit().remove(key).apply();
            }

        /**
         * Exclua o arquivo de imagem em 'caminho'
         * @param 'caminho' caminho do arquivo de imagem
         * @return true se excluído com sucesso, false caso contrário
         */

            public boolean deleteImage(String path) {
                return new File(path).delete();
            }

        /**
         * Limpar SharedPreferences (remover tudo)
         */

            public void clear() {
                preferences.edit().clear().apply();
            }

        /**
         * Recupere todos os valores de SharedPreferences. Não modifique o retorno da coleção por método
         * @return a Map representando uma lista de pares chave / valor de SharedPreferences
         */

            public Map<String, ?> getAll() {
                return preferences.getAll();
            }

         /**
          * Registrar ouvinte de alteração de SharedPreferences
          * @param listener listener object de OnSharedPreferenceChangeListener
          */

             public void registerOnSharedPreferenceChangeListener(
                     SharedPreferences.OnSharedPreferenceChangeListener listener) {

                 preferences.registerOnSharedPreferenceChangeListener(listener);
             }

         /**
          * Unregister SharedPreferences change listener
          * Objeto de ouvinte de ouvinte @param de OnSharedPreferenceChangeListener a ser cancelado
          */

             public void unregisterOnSharedPreferenceChangeListener(
                     SharedPreferences.OnSharedPreferenceChangeListener listener) {

                 preferences.unregisterOnSharedPreferenceChangeListener(listener);
             }

         /**
          * Verifique se o armazenamento externo é gravável ou não
          * @return true se gravável, false caso contrário
          */

             public static boolean isExternalStorageWritable() {
                 return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
             }

         /**
          * Verifique se o armazenamento externo é legível ou não
          * @return true se legível, false caso contrário
          */

             public static boolean isExternalStorageReadable() {
                 String state = Environment.getExternalStorageState();

                 return Environment.MEDIA_MOUNTED.equals(state) ||
                         Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
             }

         /**
          * chaves nulas podem corromper o arquivo pref compartilhado e torná-lo ilegível, esta é uma medida preventiva
          * @param key a pref key a verificar
          */

             private void checkForNullKey(String key){
                 if (key == null){
                     throw new NullPointerException();
                 }
             }

         /**
          * chaves nulas podem corromper o arquivo pref compartilhado e torná-lo ilegível, esta é uma medida preventiva
          * @param 'valor' o valor pref a verificar
          */

         private void checkForNullValue(String value){
             if (value == null){
                 throw new NullPointerException();
             }
         }
}
