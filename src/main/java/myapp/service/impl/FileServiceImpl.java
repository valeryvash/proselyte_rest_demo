package myapp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.model.File;
import myapp.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import myapp.service.FileService;

import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File getById(Long id) {
        log.info("IN FileServiceImpl getById {} ", id);
        return fileRepository.findById(id).orElse(null);
    }

    @Override
    public void save(File file) {
        log.info("IN FileServiceImpl save {} ", file);
        File fromPersistence = fileRepository.findById(file.getId()).orElse(new File());
        fromPersistence.setFileName(file.getFileName());
        fromPersistence.setFilePath(file.getFilePath());
        fileRepository.save(fromPersistence);
    }

    @Override
    public void deleteById(Long id) {
        log.info("IN FileServiceImpl deleteById {} ", id);
        fileRepository.deleteById(id);
    }

    @Override
    public List<File> getAll() {
        log.info("IN FileServiceImpl getAll {} ");
        return fileRepository.findAll();
    }
}
