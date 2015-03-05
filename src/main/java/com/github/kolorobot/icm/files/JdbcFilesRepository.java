package com.github.kolorobot.icm.files;

import com.github.kolorobot.icm.support.date.DateConverter;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
class JdbcFilesRepository implements FilesRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcFilesRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Inject
    public JdbcFilesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public File save(File file, byte[] bytes) throws IOException {
        long fileId = jdbcTemplate.queryForLong("select max(id) from files") + 1;
        String sql = "insert into files (id, name, size, content_type, file, object_id, object_type, creator_id, created) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ps -> {
            ps.setLong(1, fileId);
            ps.setString(2, file.getName());
            ps.setLong(3, file.getSize());
            ps.setString(4, file.getContentType());
            ps.setBytes(5, bytes);
            ps.setLong(6, file.getObjectId());
            ps.setString(7, file.getObjectType());
            ps.setLong(8, file.getCreatorId());
            ps.setString(9, DateConverter.toDateString(file.getCreated()));
        });


        file.setId(fileId);
        return file;
    }

    @Override
    public List<File> findAll(String objectType, Long objectId) {
        String sql = "select * from files where object_type = ? and object_id = ?";
        LOGGER.debug("Running SQL query: " + sql);
        List<File> files = jdbcTemplate.query(sql, new Object[]{objectType, objectId}, this::mapToFile);
        return files == null ? Lists.<File>newArrayList() : files;
    }

    @Override
    public File getFile(long fileId) {
        String sql = "select * from files where id = ?";
        LOGGER.debug("Running SQL query: " + sql);
        return jdbcTemplate.queryForObject(sql, new Object[]{fileId}, this::mapToFile);
    }

    @Override
    public void writeFileTo(long fileId, OutputStream outputStream) {
        String sql = "select file from files where id = ?";
        jdbcTemplate.query(sql, new Object[]{fileId}, new int[]{Types.INTEGER}, (ResultSet rs) -> {
            try {
                ByteStreams.copy(new ByteArrayInputStream(rs.getBytes("file")), outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private File mapToFile(ResultSet rs, int rowNum) throws SQLException {
        File file = new File();
        file.setId(rs.getLong("id"));
        file.setCreated(DateConverter.toDate(rs.getString("created")));
        file.setCreatorId(rs.getLong("creator_id"));
        file.setObjectId(rs.getLong("object_id"));
        file.setObjectType(rs.getString("object_type"));
        file.setName(rs.getString("name"));
        file.setContentType(rs.getString("content_type"));
        file.setSize(rs.getLong("size"));
        return file;
    }
}
