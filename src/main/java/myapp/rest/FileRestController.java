package myapp.rest;

import myapp.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import myapp.service.FileService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/files/",produces = MediaType.APPLICATION_JSON_VALUE)
public class FileRestController {

    private final FileService fileService;

    @Autowired
    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<File> getFile(@PathVariable("id") Long fileId) {
        if (fileId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        File file = fileService.getById(fileId);

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<File> saveFile(@RequestBody @Valid File file) {
        HttpHeaders headers = new HttpHeaders();

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.fileService.save(file);

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity<File> updateFile(@RequestBody @Valid File file) {
        HttpHeaders headers = new HttpHeaders();

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.fileService.save(file);

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<File> deleteFile(@PathVariable("id") Long id) {
        File file = fileService.getById(id);

        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.fileService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<File>> getAllFiles(){
        List<File> files = fileService.getAll();

        if (files == null || files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(files, HttpStatus.OK);
    }
}
