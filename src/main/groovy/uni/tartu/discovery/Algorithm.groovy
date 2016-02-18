package uni.tartu.discovery

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:38 PM
 **/

class Algorithm {
    public static void create(List records) {
        for (record in records) {
            println "$record.serviceName"
        }
    }
}