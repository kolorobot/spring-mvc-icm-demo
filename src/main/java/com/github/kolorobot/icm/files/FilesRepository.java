package com.github.kolorobot.icm.files;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;

public interface FilesRepository {

    File save(File file, byte[] bytes) throws IOException;

    List<File> findAll(String objectType, Long objectId);

    File getFile(long fileId);

    void writeFileTo(long fileId, OutputStream outputStream);
}
