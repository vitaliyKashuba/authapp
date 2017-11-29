package vitaliy94.passworder;

import com.github.defuse.PasswordStorage;
import vitaliy94.passworder.util.CollectionConverter;
import vitaliy94.passworder.util.FileUtil;

import java.io.IOException;
import java.util.Map;

/**
 * Main class for application
 *
 * uses open-source library 'password-hashing' from github
 * @see "https://github.com/defuse/password-hashing"
 *
 */
public class Main
{
    public static void main(String args[])
    {
        validateArgs(args);

        try //lib generates lot of probably impossible to catch exceptions and in case of IO program should end, that's why whole logic under try
        {
            FileUtil fileUtil = new FileUtil();
            Map<String, String> users = CollectionConverter.convertListToMap(fileUtil.read());

            String[] splittedArg = args[1].split(":");
            switch (args[0])
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
        } catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1); //in case of IO it will not work so exit
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException e) // probably impossible cath this without changing library code
        {
            e.printStackTrace();
        }
    }

    private static void validateArgs(String args[])
    {
        if ( (args.length < 2) || (!args[1].contains(":")) )
        {
            throw new IllegalArgumentException("invalid args");
        }
    }
}
