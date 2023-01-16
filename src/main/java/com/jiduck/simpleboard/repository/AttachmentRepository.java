package com.jiduck.simpleboard.repository;

import com.jiduck.simpleboard.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
