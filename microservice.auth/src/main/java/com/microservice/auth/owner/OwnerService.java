package com.microservice.auth.owner;

import com.microservice.auth.account.Account;
import com.microservice.auth.owner.dto.OwnerProfileResponse;
import com.microservice.auth.owner.dto.OwnerProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerProfileRepository ownerRepository;

    public void createOwnerProfile(Account account, String firstName, String lastName, String phone, String nationalId) {
        OwnerProfile owner = new OwnerProfile();
        owner.setAccount(account);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setPhone(phone);
        owner.setNationalId(nationalId);
        ownerRepository.save(owner);
    }

    public OwnerProfileResponse getOwner(Integer accountId) {
        OwnerProfile owner = findOwnerOrThrow(accountId);
        return new OwnerProfileResponse(
                owner.getId(),
                owner.getAccount().getEmail(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getNationalId(),
                owner.getPhone()
        );
    }

    public OwnerProfileResponse updateOwner(Integer accountId, OwnerProfileUpdateRequest request) {
        OwnerProfile owner = findOwnerOrThrow(accountId);
        owner.setFirstName(request.firstName());
        owner.setLastName(request.lastName());
        owner.setNationalId(request.nationalId());
        owner.setPhone(request.phone());
        ownerRepository.save(owner);
        return getOwner(accountId);
    }

    public OwnerProfile findOwnerOrThrow(Integer accountId) {

        return ownerRepository.findByAccountId(accountId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Dueño no encontrado con accountId: "
                                        + accountId));
    }

    public ProfileInfo getProfileInfo(Integer accountId) {
        return ownerRepository.findByAccountId(accountId)
                .map(o -> new ProfileInfo(o.getFirstName(), o.getLastName(), o.getId()))
                .orElse(new ProfileInfo(null, null, null));
    }

    public record ProfileInfo(String firstName, String lastName, Integer profileId) {}


}
