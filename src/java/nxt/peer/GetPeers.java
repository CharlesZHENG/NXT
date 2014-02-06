package nxt.peer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

final class GetPeers extends HttpJSONRequestHandler {

    static final GetPeers instance = new GetPeers();

    private GetPeers() {}


    @Override
    public JSONObject processJSONRequest(JSONObject request, Peer peer) {

        JSONObject response = new JSONObject();

        JSONArray peers = new JSONArray();
        for (Peer otherPeer : Peer.getAllPeers()) {

            if (otherPeer.getBlacklistingTime() == 0 && otherPeer.getAnnouncedAddress().length() > 0
                    && otherPeer.getState() == Peer.State.CONNECTED && otherPeer.shareAddress()) {

                peers.add(otherPeer.getAnnouncedAddress());

            }

        }
        response.put("peers", peers);

        return response;
    }

}