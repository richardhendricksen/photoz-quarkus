package nl.codecontrol.util;

import javax.ws.rs.core.MediaType;

public record MultiPartFile(String fileName, byte[] data, MediaType contentType) {}
