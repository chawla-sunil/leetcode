
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BitoOA {
    // It is OA (Online Assessment) round questions not interview
    public static int minParentheses(String s) {
//        Minimum number of Parentheses to be added to make it valid
//        Input: str = "())("
//        Output: 2
//        One '(' is required at beginning and One ')' at end.

        while (s.contains("()")) {
            s = s.replace("()", "");
        }
        return s.length();
    }

    List<Integer> sentTimes(int numberOfPorts, int transmissionTime, List<Integer> packedIDs) {
//        Hashed Ports Packets are sent to different ports on a computer system based on the hash of their packet ID.
//        The value of the hash is as given below.
//        Hash = mod (packet_id, numberofPorts)
//        where mod is the modulus operator and takes the mod of first operand by second operand.
//        The ports are numbered from 0 to (number of ports)-1, and a packet is initially sent to the port
//        that has the port number equal to the hash of its packet ID.
//        Each port requires a time f to send a packet. If a port is currently sending a packet, this packet is
//        then sent to the next port number, and so on.
//        Given that * packets arrive 7 per second, and given the IDs of the packets,
//        find the port at which each packet is finally sent. First packet is sent at time ( = 1.
//        Function Description Complete the sentTimes function in the editor below.
//        The function must return an integer array denoting the ports at which the packets are sent.
//        sent Times has the following parameter(s): numberOfPorts: An integer, the number of ports in the system.
//        transmissionTime: An integer, the time for a port to send a packet.
//        packetids. An integer array, where packetids, describes the IDs of the packets in the order in which
//        they arrive.
//        Constraints . 1's number OfPorts s 2000 1 &amp; transmission Time s 100 . 1 5 x 6 2000 . 1 &amp; pocketids, $ 105
        List<Integer> output = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int currentTimeNow = 0;
        for (int i = 0; i < numberOfPorts; i++) {
            map.put(i, 0);
        }
        for (int packetId: packedIDs) {
            int hash = packetId % numberOfPorts;
            while (map.get(hash) > currentTimeNow) {
                hash = (hash + 1) % numberOfPorts;
            }
            output.add(hash);
            map.put(hash, transmissionTime + currentTimeNow);
            currentTimeNow++;
        }
        return output;
    }

    //  3rd problem was a SQL problem.
    //  SELECT protocol, SUM(traffic_in) AS traffic_in, SUM(traffic_out) AS traffic_out
    //  FROM traffic
    //  GROUP BY protocol
    //  HAVING SUM(traffic_in) > SUM(traffic_out)
    //  ORDER BY traffic_in ASC

}
