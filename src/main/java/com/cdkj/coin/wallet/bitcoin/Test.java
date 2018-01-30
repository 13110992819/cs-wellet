/**
 * @Title Test.java 
 * @Package com.cdkj.coin.bitcoin 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年1月3日 下午9:02:08 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.bitcoin;

import org.bitcoinj.core.Block;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.MemoryBlockStore;
import org.bitcoinj.utils.BlockFileLoader;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月3日 下午9:02:08 
 * @history:
 */
public class Test {

    /** 
     * @param args 
     * @create: 2018年1月3日 下午9:02:08 haiqingzheng
     * @history: 
     */
    public static void main(String[] args) {

        // NetworkParameters params = MainNetParams.get();
        //
        // ECKey key = new ECKey();
        // System.out.println("We created a new key:\n" + key);
        //
        // Address addressFromKey = key.toAddress(params);
        // System.out.println("Public Address generated: " + addressFromKey);
        //
        // System.out.println("Private key is: "
        // + key.getPrivateKeyEncoded(params).toString());
        //
        // Wallet wallet = new Wallet(params);
        // wallet.importKey(key);

        final NetworkParameters netParams = MainNetParams.get();

        Block genesisBlock = netParams.getGenesisBlock();

        System.out.println(genesisBlock);

        BlockStore blockStore = new MemoryBlockStore(netParams);

        BlockFileLoader loader = new BlockFileLoader(netParams,
            BlockFileLoader.getReferenceClientBlockFileList());

        for (Block block : loader) {
            System.out.println(block);
        }
        Transaction t = genesisBlock.getTransactions().get(0);
        System.out.println(t);

        // try {
        // BlockChain chain = new BlockChain(netParams, blockStore);
        // } catch (BlockStoreException e) {
        // e.printStackTrace();
        // }

        // genesisBlock.getPrevBlockHash()

        // MainNetParams.get().

    }
}
