package org.example;

import org.example.commands.IncreaseCommand;
import org.example.commands.ResetCommand;
import org.example.commands.StopCommand;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    static void Serialize(Counter counter, Path counterFilePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(counterFilePath.toFile()))) {
            outputStream.writeObject(counter);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static Counter Deserialize(Path counterFilePath) {
        try (ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(counterFilePath.toFile()))) {
            return (Counter) objectStream.readObject();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static Counter tryDeserialize(Path counterFilePath) {
        if (Files.exists(counterFilePath))
            return Utils.Deserialize(counterFilePath);
        else
            return new CounterImpl();
    }

    static void addCommands(Counter counter) {
        IncreaseCommand increaseCommand = new IncreaseCommand(Main.counterStateMachine, counter);
        ResetCommand resetCommand = new ResetCommand(Main.counterStateMachine, counter);
        StopCommand stopCommand = new StopCommand(Main.counterStateMachine, counter);

        Main.commands.put(increaseCommand.getName(), increaseCommand);
        Main.commands.put(resetCommand.getName(), resetCommand);
        Main.commands.put(stopCommand.getName(), stopCommand);
    }
}
