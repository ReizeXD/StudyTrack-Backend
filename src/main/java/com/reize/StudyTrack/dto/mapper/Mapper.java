package com.reize.StudyTrack.dto.mapper;

import com.reize.StudyTrack.dto.user.UserResponseDTO;
import com.reize.StudyTrack.entity.User;

public class Mapper {
    public static UserResponseDTO toUserDto(User user){
        return new UserResponseDTO(user.getId(), user.getName(),user.getEmail(),user.getBio(), user.getLevel(), user.getPoints());
    }
}
