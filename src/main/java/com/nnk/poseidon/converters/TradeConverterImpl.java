package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.dto.TradeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Trade converter.
 */
@Component
public class TradeConverterImpl implements TradeConverter {

    /**
     * ModelMapper to inject.
     */
    private final ModelMapper mapper;

    /**
     * Instantiates a new TradeConverter.
     *
     * @param modelMapper the modelMapper
     */
    @Autowired
    public TradeConverterImpl(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * Converts a TradeDTO to a Trade entity.
     *
     * @param tradeDTO the TradeDTO that will be converted
     * @return a Trade entity
     */
    @Override
    public Trade tradeDTOToTradeEntityConverter(final TradeDTO tradeDTO) {
        return mapper.map(tradeDTO, Trade.class);
    }

    /**
     * Converts a list of TradeDTOs to a list of Trade entities.
     *
     * @param tradeDTOList the TradeDTO list that will be converted
     * @return a list of Trade entities
     */
    @Override
    public List<Trade> tradeDTOListToTradeEntityListConverter(
            final List<TradeDTO> tradeDTOList) {
        return tradeDTOList.stream()
                .map(this::tradeDTOToTradeEntityConverter)
                .collect(Collectors.toList());
    }

    /**
     * Converts a Trade entity to a TradeDTO.
     *
     * @param trade the Trade entity to convert
     * @return a TradeDTO
     */
    @Override
    public TradeDTO tradeEntityToTradeDTOConverter(final Trade trade) {
        return mapper.map(trade, TradeDTO.class);
    }

    /**
     * Converts a list of Trade entities to a list of TradeDTOs.
     *
     * @param tradeList the list of Trade entities to convert
     * @return a list of TradeDTO
     */
    @Override
    public List<TradeDTO> tradeEntityListToTradeDTOListConverter(
            final List<Trade> tradeList) {
        return tradeList.stream()
                .map(this::tradeEntityToTradeDTOConverter)
                .collect(Collectors.toList());
    }
}
