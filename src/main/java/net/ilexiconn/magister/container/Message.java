package net.ilexiconn.magister.container;

import com.google.gson.annotations.SerializedName;
import net.ilexiconn.magister.container.sub.Link;

public class Message {
    @SerializedName("Id")
    public int id;

    @SerializedName("MapId")
    public int mapId;

    @SerializedName("MapTitel")
    public String mapTitel;

    @SerializedName("Links")
    public Link[] links;

    @SerializedName("Onderwerp")
    public String onderwerp;

    /* TODO: Afzender */

    @SerializedName("IngekortBericht")
    public String ingekortBericht;

    /* TODO: ONtvangers */

    @SerializedName("VerstuurdOp")
    public String verstuurdOp;

    @SerializedName("IsGelezen")
    public boolean isGelezen;

    @SerializedName("Status")
    public int status;

    @SerializedName("HeeftPrioriteit")
    public boolean heeftPrioriteit;

    @SerializedName("HeeftBijlagen")
    public boolean heeftBijlagen;

    @SerializedName("Soort")
    public int soort;
}