/**
 * 冲突处理使用拉链法
 */
class BKDRHashTable{
    private Hash_Node hash_table_main;

    private Hash_Node hash_node_pool;

    private Integer hash_table_len, node_pool_len, node_pool_cnt;
    /**
     * 采用BKDRHash
     */
    public static Integer hash(String name){
        int hash, seed = 131;

        for(int i = 0;i < name.length; i++){
            hash = hash  * seed+name[i];
        }
        return (hash & 0x7fffffff);
    }

    /**
     * 获取数组下表标
     */
    public Integer getHashIndex(String str){
        if (this.hash_table_len ==0) {
            return 0;
        }
        return (BKDRHashTable.hash(str)%this.hash_table_len);
    }
    
    /**
     * 获取新节点
     */
    public Hash_Node getNowNode(){
        if(this.node_pool_cnt>=this.node_pool_len) return null;
        
        return &this.hash_node_pool[this.node_pool_cnt++];
    }

    /*
    查询
    */
    public boolean search(String str){

        int hash_index = this.getNowNode(str);
        
        Hash_Node p = this.hash_table_main[hash_index];

        while (p != null) {
            if(p.getDate() == str) return true;

            p = p.getNext;
        }
        return false;
    }

    /**
     * 插入
     */
    boolean insert(String str,boolean &is_uniq){
        if(true == this.search(str)){
            is_uniq = false;
            return true;
        }

        Hash_Node p,q = this.getNowNode();

        if(q == null) return false;

        int hash_index = this.getHashIndex(str);

        q.setDate(str); q.setNext(p);

        this.hash_table_main[hash_index] = q;

        is_uniq = true;

        return true;
    }

    public static void main(String[] args) {
        BKDRHashTable ht = new BKDRHashTable(100,1000);

        boolean is_uniq,flag;

        ht.insert("AOA",is_uniq);
    }
}


class Hash_Node{

    private String date;

    private Hash_Node next;

    public Hash_Node(String date,Hash_Node next){
        this.date = date;
        this.next = next;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public void setNext(Hash_Node next){
        this.next = next;
    }

    public Hash_Node getNext(){
        return next;
    }
}