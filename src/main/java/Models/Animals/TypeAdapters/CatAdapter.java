package Models.Animals.TypeAdapters;

import Models.Animals.Cat;
import Models.Enums.AnimalType;
import Models.Enums.Gender;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;

public class CatAdapter extends TypeAdapter<Cat>
{
    @Override
    public Cat read(JsonReader reader) throws IOException
    {
        Cat cat = new Cat();
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()){
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)){
                fieldname = reader.nextName();
            }

            if ("animalType".equals(fieldname)){
                token = reader.peek();

                if (reader.nextString() == "Cat"){
                    cat.setAnimalType(AnimalType.Cat);
                }
            }

            if ("name".equals(fieldname)){
                token = reader.peek();
                cat.setName(reader.nextString());
            }

            if ("gender".equals(fieldname)){
                token = reader.peek();

                if (reader.nextString().equals("Male")){
                    cat.setGender(Gender.Male);
                }
                else if (reader.nextString().equals("Female")){
                    cat.setGender(Gender.Female);
                }
            }

            if ("price".equals(fieldname)){
                token = reader.peek();
                cat.setPrice(reader.nextDouble());
            }

            if("lastWalk".equals(fieldname)){
                token = reader.peek();
                cat.setBadHabits(reader.nextString());
            }
        }
        reader.endObject();
        return cat;
    }

    @Override
    public void write(JsonWriter jsonWriter, Cat cat) throws IOException
    {

    }
}
