package vlaship.price.h2.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vlaship.price.h2.service.DownloadCsvService;
import vlaship.price.h2.service.ParserService;
import vlaship.price.h2.service.UpdateDbService;
import vlaship.price.h2.service.UploadDataService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateDbServiceImpl implements UpdateDbService {

    private final DownloadCsvService downloadCsvService;
    private final ParserService parserService;
    private final UploadDataService uploadDataService;

    @Override
    public void update() {
        log.info("Start updating DB ... ");
        final var lines = downloadCsvService.download();
        final var products = parserService.parse(lines);
        uploadDataService.upload(products);
        log.info("DB has been updated");
    }
}
