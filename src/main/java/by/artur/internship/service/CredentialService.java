package by.artur.internship.service;

import by.artur.internship.exception.NotFoundException;
import by.artur.internship.model.dao.Credential;
import by.artur.internship.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository credentialRepository;

    public Credential getCredentials(Long credentialId) {
        return credentialRepository.findById(credentialId).get();
    }

    public void createCredential(Credential credential) {
        credentialRepository.save(credential);
    }

    public void updateCredential(Long credentialId, Credential credential) {
        Optional<Credential> optionalCredential = credentialRepository.findById(credentialId);
        if (optionalCredential.isPresent()) {
            Credential updatedCredential = optionalCredential.get();
            updatedCredential.setEmail(credential.getEmail());
            updatedCredential.setPassword(credential.getPassword());
            credentialRepository.save(updatedCredential);
        } else {
            throw new NotFoundException("Credential not found");
        }
    }

    public void deleteCredential(Credential credential) {
        credentialRepository.delete(credential);
    }

}
