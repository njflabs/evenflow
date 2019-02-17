package com.njfsoft_utils.shoputil;
 
public class ShopItemArr {
    String tagValA;


/*

_id
i_rtype
i_itemid
i_bcode
i_uid
i_coid
i_catid
i_pid
i_title
i_desc
i_price_a
i_price_b
i_dimen_n
i_dimen_v
i_img
i_vala
i_valb
i_dadded

*/
    public ShopItemArr() {
    }

    int key_i_id;
    int key_i_rtype;
    String key_i_itemid;
    String key_i_bcode;
    String key_i_uid;
    String key_i_coid;
    String key_i_catid;
    String key_i_pid;

    public int get_key_i_id() {
        return key_i_id;
    }

    public int get_key_i_rtype() {
        return key_i_rtype;
    }

    public String get_key_i_itemid() {
        return key_i_itemid;
    } 

    @Override
    public String toString() {
        return key_i_itemid;
    }
}
