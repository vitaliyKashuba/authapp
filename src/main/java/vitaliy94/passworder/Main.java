package vitaliy94.passworder;

import com.github.defuse.PasswordStorage;
import vitaliy94.passworder.util.CollectionConverter;
import vitaliy94.passworder.util.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String args[]) throws PasswordStorage.CannotPerformOperationException, IOException, PasswordStorage.InvalidHashException {
        String argss[] = new String[2];
        argss[0] = "add";
        argss[1] = "username1:password1";
//        if ( (args.length < 2) || (!args[1].contains(":")) )
//        {
//            throw new IllegalArgumentException("invalid args");
//        }
        FileUtil fileUtil = new FileUtil();

        Map<String, String> users = CollectionConverter.convertListToMap(fileUtil.read());

        String[] splittedArg = argss[1].split(":");
        switch (argss[0])
        {
            case "add":
                if (users.containsKey(splittedArg[0]))
                {
                    throw new IllegalArgumentException("such username already exists");
                }
                users.put(splittedArg[0], PasswordStorage.createHash(splittedArg[1]));
                fileUtil.write(CollectionConverter.convertMapToList(users));
                break;
            case "auth":
                if(!PasswordStorage.verifyPassword(splittedArg[1], users.get(splittedArg[0])))
                {
                    System.exit(401);
                }
                break;
            default:
                throw new IllegalArgumentException("unknown command line argument \"" + args[0] + "\"");
        }

    }

}
