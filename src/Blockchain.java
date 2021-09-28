import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Blockchain
{
//    Stores the object of the linked list, since block is stored in chronological order,
//    it would keep the change in a binary format.
//    Access the file to get the previous hash to add the block to the chain.
    private static final String BCHAIN_FILE = "master/chain.dat";

//    source - gets it from the file stored in the memory
    private static LinkedList<Block> db = new LinkedList<>();

//    operations: persist, get, distribute blockchain
//    write to the masterchain file (persist)

    private static void persist()
    {
//        helpers: FileOutputStream & ObjectOutputStream
        try
                (
                        FileOutputStream fos = new FileOutputStream(BCHAIN_FILE);
                        ObjectOutputStream out = new ObjectOutputStream(fos);
                        )

        {
//            write db object to the file
            out.writeObject(db);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    obtain the existing chain
    public static LinkedList<Block>get()
    {
//        helpers: FileInputStream and ObjectInputStream
        try(
                FileInputStream fis = new FileInputStream(BCHAIN_FILE);
                ObjectInputStream in = new ObjectInputStream(fis);
                )
        {
            return (LinkedList<Block>)in.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

//    display/write the Blockchain
    public static void distribute()
    {
//        display the Blockchain
        String chain = new GsonBuilder()
                            .setPrettyPrinting()
                            .create()
                            .toJson(db);
        try
        {
            Files.write(Paths.get("ledger.txt"), chain.getBytes(), StandardOpenOption.CREATE);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Blockchain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    Append new data to the Ledger.
    public static void distributeAppend()
    {
//        display the Blockchain
        String chain = new GsonBuilder()
                            .setPrettyPrinting()
                            .create()
                            .toJson(db);
        try
        {
            Files.write(Paths.get("ledger.txt"), chain.getBytes(), StandardOpenOption.SPARSE);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Blockchain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    add a new block to the existing Blockchain
    public static void nextBlock(Block newBlock)
    {
        Blockchain.db.add(newBlock);
        Blockchain.persist();
    }

//    Read the ledger.txt file.
    public static List<String> readLedger(String DBNoticeID)
    {
        Gson gsonRead = new Gson();
        List<String> ledgerData = new ArrayList<>();
        try
        {
            FileReader fileReader = new FileReader("ledger.txt");
            BufferedReader buffered = new BufferedReader(fileReader);
            Block[] userArray = gsonRead.fromJson(buffered, Block[].class);

            for (Block block : userArray)
            {
                if (DBNoticeID.equals(block.getNoticeID()))
                {
                    ledgerData.add(block.getNoticeID());
                    ledgerData.add(block.getIDCategory());
                    ledgerData.add(block.getNoticeType());
                    ledgerData.add(block.getOffenseType());
                    ledgerData.add(block.getOffenseLocation());
                    ledgerData.add(block.getOffenseDate());
                    ledgerData.add(block.getPaymentDate());
                }
            }
        }
        catch (Exception ex)
        {
        }

        return ledgerData;
    }

}
