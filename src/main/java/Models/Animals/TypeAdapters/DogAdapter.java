package Models.Animals.TypeAdapters;

import Models.Animals.Dog;
import Models.Enums.AnimalType;
import Models.Enums.Gender;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;

public class DogAdapter extends TypeAdapter<Dog>
{
    @Override
    public Dog read(JsonReader reader) throws IOException
    {
        Dog dog = new Dog();
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()){
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)){
                fieldname = reader.nextName();
            }

            if ("animalType".equals(fieldname)){
                token = reader.peek();

                if (reader.nextString() == "Dog"){
                    dog.setAnimalType(AnimalType.Dog);
                }
            }

            if ("name".equals(fieldname)){
                token = reader.peek();
                dog.setName(reader.nextString());
            }

            if ("gender".equals(fieldname)){
                token = reader.peek();

                if (reader.nextString().equals("Male")){
                    dog.setGender(Gender.Male);
                }
                else if (reader.nextString().equals("Female")){
                    dog.setGender(Gender.Female);
                }
            }

            if ("price".equals(fieldname)){
                token = reader.peek();
                dog.setPrice(reader.nextDouble());
            }

            if("lastWalk".equals(fieldname)){
                token = reader.peek();
                dog.setLastWalk(LocalDateTime.parse(reader.nextString()));
            }
        }
        reader.endObject();
        return dog;
    }

    @Override
    public void write(JsonWriter jsonWriter, Dog dog) throws IOException
    {

    }
}
