package io.beatmaps.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = MapTagSlugSerializable::class)
enum class MapTag(val type: MapTagType, val human: String, val slug: String) {
    None(MapTagType.None, "", ""),

    Rock(MapTagType.Style, "Rock", "rock"),
    Metal(MapTagType.Style, "Metal", "metal"),
    Pop(MapTagType.Style, "Pop", "pop"),
    Electronic(MapTagType.Style, "Electronic", "electronic"),
    Chinese(MapTagType.Style, "Chinese", "chinese"),
    Anime(MapTagType.Style, "Anime", "anime"),

    Blues(MapTagType.Genre, "Blues", "blues"),
    RB(MapTagType.Genre, "R&B", "r-b"),
    KPop(MapTagType.Genre, "K-Pop", "k-pop"),
    JPop(MapTagType.Genre, "J-Pop", "j-pop"),
    Funk(MapTagType.Genre, "Funk", "funk"),
    Folk(MapTagType.Genre, "Folk", "folk"),
    House(MapTagType.Genre, "House", "house"),
    Hiphop(MapTagType.Genre, "Hip-hop", "hip-hop"),
    Jazz(MapTagType.Genre, "Jazz", "jazz"),
    Fusion(MapTagType.Genre, "Fusion", "fusion"),
    AlternativeRock(MapTagType.Genre, "Alternative rock", "alternative-rock"),
    Punk(MapTagType.Genre, "Punk", "punk"),
    Soul(MapTagType.Genre, "Soul", "soul"),
    Reggae(MapTagType.Genre, "Reggae", "reggae"),
    Latin(MapTagType.Genre, "Latin", "latin"),
    Disco(MapTagType.Genre, "Disco", "disco"),
    Indie(MapTagType.Genre, "Indie", "indie"),
    Kids(MapTagType.Genre, "Kids", "kids"),
    TVFilm(MapTagType.Genre, "TV&Film", "tv-film"),
    VideoGame(MapTagType.Genre, "Video Game", "video-game"),
    OldSchool(MapTagType.Genre, "Old School", "old-school"),
    AvantGarde(MapTagType.Genre, "Avant-garde", "avant-garde"),
    Experimental(MapTagType.Genre, "Experimental", "experimental"),
    Instrumental(MapTagType.Genre, "Instrumental", "instrumental");
//    Tech(MapTagType.Style, "Tech", "tech"),
//    DanceStyle(MapTagType.Style, "Dance", "dance-style"),
//    Speed(MapTagType.Style, "Speed", "speed"),
//    Balanced(MapTagType.Style, "Balanced", "balanced"),
//    Challenge(MapTagType.Style, "Challenge", "challenge"),
//    Accuracy(MapTagType.Style, "Accuracy", "accuracy"),
//    Fitness(MapTagType.Style, "Fitness", "fitness"),
//
//    Swing(MapTagType.Genre, "Swing", "swing"),
//    Nightcore(MapTagType.Genre, "Nightcore", "nightcore"),
//    Folk(MapTagType.Genre, "Folk & Acoustic", "folk-acoustic"),
//    Family(MapTagType.Genre, "Kids & Family", "kids-family"),
//    Ambient(MapTagType.Genre, "Ambient", "ambient"),
//    Funk(MapTagType.Genre, "Funk & Disco", "funk-disco"),
//    Jazz(MapTagType.Genre, "Jazz", "jazz"),
//    Classical(MapTagType.Genre, "Classical & Orchestral", "classical-orchestral"),
//    Soul(MapTagType.Genre, "Soul", "soul"),
//    Speedcore(MapTagType.Genre, "Speedcore", "speedcore"),
//    Punk(MapTagType.Genre, "Punk", "punk"),
//    RB(MapTagType.Genre, "R&B", "rb"),
//    Holiday(MapTagType.Genre, "Holiday", "holiday"),
//    Vocaloid(MapTagType.Genre, "Vocaloid", "vocaloid"),
//    JRock(MapTagType.Genre, "J-Rock", "j-rock"),
//    Trance(MapTagType.Genre, "Trance", "trance"),
//    DrumBass(MapTagType.Genre, "Drum and Bass", "drum-and-bass"),
//    Comedy(MapTagType.Genre, "Comedy & Meme", "comedy-meme"),
//    Instrumental(MapTagType.Genre, "Instrumental", "instrumental"),
//    Hardcore(MapTagType.Genre, "Hardcore", "hardcore"),
//    KPop(MapTagType.Genre, "K-Pop", "k-pop"),
//    Indie(MapTagType.Genre, "Indie", "indie"),
//    Techno(MapTagType.Genre, "Techno", "techno"),
//    House(MapTagType.Genre, "House", "house"),
//    Game(MapTagType.Genre, "Video Game", "video-game-soundtrack"),
//    Film(MapTagType.Genre, "TV & Film", "tv-movie-soundtrack"),
//    Alt(MapTagType.Genre, "Alternative", "alternative"),
//    Dubstep(MapTagType.Genre, "Dubstep", "dubstep"),
//    Metal(MapTagType.Genre, "Metal", "metal"),
//    Anime(MapTagType.Genre, "Anime", "anime"),
//    HipHop(MapTagType.Genre, "Hip Hop & Rap", "hip-hop-rap"),
//    JPop(MapTagType.Genre, "J-Pop", "j-pop"),
//    Dance(MapTagType.Genre, "Dance", "dance"),
//    Rock(MapTagType.Genre, "Rock", "rock"),
//    Pop(MapTagType.Genre, "Pop", "pop"),
//    Electronic(MapTagType.Genre, "Electronic", "electronic");

    companion object {
        private val map = values().associateBy(MapTag::slug)
        fun fromSlug(slug: String) = map[slug]

        val maxPerType = mapOf(
            MapTagType.Style to 1,
            MapTagType.Genre to 3
        ).withDefault { 0 }

        val sorted = values().sortedWith(compareBy({ it.type.ordinal }, { it.human }))
    }
}

class MapTagSlugSerializable : KSerializer<MapTag> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("MapTag", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): MapTag = MapTag.fromSlug(decoder.decodeString()) ?: MapTag.None
    override fun serialize(encoder: Encoder, value: MapTag) = encoder.encodeString(value.slug)
}

enum class MapTagType(val color: String) {
    None(""), Style("blue"), Genre("green");
}
