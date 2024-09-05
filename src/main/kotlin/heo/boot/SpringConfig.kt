package heo.boot

import heo.boot.mapper.AddressMapper
import heo.boot.service.AddressService
import heo.boot.service.MybatisAddressService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SpringConfig(private val dataSource: DataSource, private val addressMapper: AddressMapper) {

    @Bean
    fun addressService(): AddressService {
        return MybatisAddressService(addressMapper)
    }

}

