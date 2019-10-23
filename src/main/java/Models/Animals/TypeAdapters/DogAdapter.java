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

            LocalDateTime localDateTime = null;

            if("year".equals(fieldname)){
                token = reader.peek();
                localDateTime.withYear(Integer.parseInt(reader.nextString()));
            }

            if("month".equals(fieldname)){
                token = reader.peek();
                localDateTime.withMonth(Integer.parseInt(reader.nextString()));
            }

            if("day".equals(fieldname)){
                token = reader.peek();
                localDateTime.withDayOfMonth(Integer.parseInt(reader.nextString()));
            }

            if("hour".equals(fieldname)){
                token = reader.peek();
                localDateTime.withHour(Integer.parseInt(reader.nextString()));
            }

            if("minute".equals(fieldname)){
                token = reader.peek();
                localDateTime.withMinute(Integer.parseInt(reader.nextString()));
            }

            if("second".equals(fieldname)){
                token = reader.peek();
                localDateTime.withSecond(Integer.parseInt(reader.nextString()));
            }
            if("nano".equals(fieldname)){
                token = reader.peek();
                localDateTime.withNano(Integer.parseInt(reader.nextString()));
            }

            dog.setLastWalk(localDateTime);

        }
        reader.endObject();
        return dog;
    }

    @Override
    public void write(JsonWriter jsonWriter, Dog dog) throws IOException
    {

    }
}
