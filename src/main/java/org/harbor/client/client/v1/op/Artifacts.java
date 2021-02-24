package org.harbor.client.client.v1.op;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.harbor.client.client.model.Artifact;
import org.harbor.client.client.model.ArtifactListFilter;
import org.harbor.client.client.v1.DefaultHarborClientV1;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author lr
 * @date 2021/2/24
 */
public class Artifacts {

    private final String repositoryBaseApi;
    private final String repositoryName;
    private final DefaultHarborClientV1 client;

    public Artifacts(DefaultHarborClientV1 client, String repositoryBaseApi, String repositoryName) {
        this.client = client;
        this.repositoryBaseApi = repositoryBaseApi;
        this.repositoryName = repositoryName;
    }

    public List<Artifact> list(ArtifactListFilter filter) {
        String artifactBaseApi = getArtifactBaseApi();
        if (filter == null) {
            filter = ArtifactListFilter.DEFAULT;
        }
        int page = filter.getPage();
        int pageSize = filter.getPageSize();
        String query = filter.getQuery();
        boolean withTag = filter.isWithTag();
        boolean withLabel = filter.isWithLabel();
        boolean withScanOverview = filter.isWithScanOverview();
        artifactBaseApi += "?page=" + page + "&page_size=" + pageSize;
        if (StrUtil.isNotEmpty(query)) {
            artifactBaseApi += "&q=" + query;
        }
        if (withTag) {
            artifactBaseApi += "&with_tag=true";
        }
        if (withLabel) {
            artifactBaseApi += "&with_label=true";
        }
        if (withScanOverview) {
            artifactBaseApi += "&with_scan_overview=true";
        }
        List<Artifact> list = client.list(artifactBaseApi, Artifact.class);
        return list;
    }

    public ArtifactHandler artifact(String reference) {
        return new ArtifactHandler(getArtifactBaseApi(), reference, this);
    }

    protected DefaultHarborClientV1 getClient() {
        return client;
    }

    public String getArtifactBaseApi() {
        return repositoryBaseApi + "/" + HttpUtil.encodeParams(repositoryName, StandardCharsets.UTF_8) + "/artifacts";
    }

}
