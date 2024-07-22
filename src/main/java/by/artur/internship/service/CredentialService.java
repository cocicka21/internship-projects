package by.artur.internship.service;

import by.artur.internship.dto.CredentialDto;
import by.artur.internship.entity.Credential;
import by.artur.internship.exception.NotFoundException;
import by.artur.internship.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository credentialRepository;
    private final ModelMapper mapper;

    public CredentialDto getCredentials(Long credentialId) {
        Credential credential = credentialRepository.findById(credentialId)
                .orElseThrow(() -> new NotFoundException("Credential is not found"));
        return mapper.map(credential, CredentialDto.class);
    }

    public CredentialDto createCredential(Credential credential) {
        credentialRepository.save(credential);
        return mapper.map(credential, CredentialDto.class);
    }

    public CredentialDto updateCredential(Long credentialId, Credential credential) {
        Credential updatedCredential = credentialRepository.findById(credentialId).orElseThrow(() -> new NotFoundException("Credential is not found"));
        updatedCredential.setEmail(credential.getEmail());
        updatedCredential.setPassword(credential.getPassword());
        credentialRepository.save(updatedCredential);
        return mapper.map(updatedCredential, CredentialDto.class);
    }

    public void deleteCredential(Credential credential) {
        credentialRepository.delete(credential);
    }

}
