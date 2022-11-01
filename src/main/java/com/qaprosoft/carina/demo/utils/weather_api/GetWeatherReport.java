package com.qaprosoft.carina.demo.utils.weather_api;


import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.demo.utils.Encryption;


@Endpoint(url = "${config.env.api_url}${api_request}&appid=${api_key}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/weather_reports/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetWeatherReport extends AbstractApiMethodV2 {

    public GetWeatherReport() {
        replaceUrlPlaceholder("api_key", Encryption.getInstance().decrypt("{crypt:2iX/g5uTDhl2doJV7dhOq9urCdV1M3+x6MkDlCdNgBBbfUQd0L8gb7qyUyDn3jHt}"));
    }
}