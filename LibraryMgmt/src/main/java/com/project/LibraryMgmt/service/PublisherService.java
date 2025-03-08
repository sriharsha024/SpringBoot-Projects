package com.project.LibraryMgmt.service;

import com.project.LibraryMgmt.entity.Publisher;
import com.project.LibraryMgmt.exception.ResourceNotFoundException;
import com.project.LibraryMgmt.payload.PublisherDTO;
import com.project.LibraryMgmt.repo.PublisherRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepo publisherRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<PublisherDTO> getAllPublishers() {
        List<Publisher> publishers = publisherRepo.findAll();
        List<PublisherDTO> publisherDTOS = publishers.stream().map(publisher -> modelMapper.map(publisher, PublisherDTO.class)).collect(Collectors.toList());
        return publisherDTOS;
    }

    public PublisherDTO getPublisherById(long id) {
        Publisher publisher = publisherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", id));
        return modelMapper.map(publisher, PublisherDTO.class);
    }

    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = modelMapper.map(publisherDTO, Publisher.class);
        publisherRepo.save(publisher);
        return modelMapper.map(publisher, PublisherDTO.class);
    }

    public PublisherDTO updatePublisher(long id, PublisherDTO publisherDTO) {
        Publisher publisherFromDB = publisherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", id));

        publisherFromDB.setPublisherName(publisherDTO.getPublisherName());
        Publisher updatedPublisher = publisherRepo.save(publisherFromDB);
        return modelMapper.map(updatedPublisher, PublisherDTO.class);
    }

    public boolean deletePublisher(long id) {
        Publisher publisher = publisherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", id));
        publisherRepo.delete(publisher);
        return true;
    }
}
