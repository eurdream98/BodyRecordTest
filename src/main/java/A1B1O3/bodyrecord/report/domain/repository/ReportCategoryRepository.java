package A1B1O3.bodyrecord.report.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportCategoryRepository extends JpaRepository<ReportCategory, Integer> {

    Optional<ReportCategory> findByReportcategoryCode(int reportcategoryCode);

    Optional<ReportCategory> findByReportcategoryName(String reportcategoryName);
}
